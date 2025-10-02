package de.julianweinelt.ubm.core;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

public class ChunkTransformer implements IClassTransformer {

    private static final int MIN_Y = -64;
    private static final int MAX_Y = 255;

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if ("net.minecraft.world.chunk.Chunk".equals(transformedName)) {
            System.out.println("[Coremod] Patching Chunk for extended height...");
            return patchChunk(basicClass);
        }
        return basicClass;
    }

    private byte[] patchChunk(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        ClassVisitor visitor = new ClassVisitor(Opcodes.ASM5, writer) {

            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

                // 1️⃣ Konstruktor patchen: storageArrays vergrößern
                if ("<init>".equals(name)) {
                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        @Override
                        public void visitInsn(int opcode) {
                            if (opcode == Opcodes.RETURN) {
                                // Array vergrößern via ASM
                                super.visitVarInsn(Opcodes.ALOAD, 0); // this
                                int newLength = (MAX_Y - MIN_Y + 16) / 16;
                                super.visitIntInsn(Opcodes.SIPUSH, newLength);
                                // ANEWARRAY nur via String
                                super.visitTypeInsn(Opcodes.ANEWARRAY, "net/minecraft/world/Chunk$ExtendedBlockStorage");
                                super.visitFieldInsn(Opcodes.PUTFIELD, "net/minecraft/world/chunk/Chunk", "storageArrays", "[Lnet/minecraft/world/Chunk$ExtendedBlockStorage;");
                                System.out.println("[Coremod] ExtendedBlockStorage array patched in constructor.");
                            }
                            super.visitInsn(opcode);
                        }
                    };
                }

                // 2️⃣ getBlockState / setBlockState patchen
                if (name.equals("getBlockState") || name.equals("func_177435_g") ||
                        name.equals("setBlockState") || name.equals("func_180501_a")) {

                    System.out.println("[Coremod] Patching " + name + " for MIN_Y offset...");

                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        @Override
                        public void visitMethodInsn(int opcode, String owner, String methodName, String desc, boolean itf) {
                            if (opcode == Opcodes.INVOKEVIRTUAL
                                    && owner.equals("net/minecraft/util/math/BlockPos")
                                    && methodName.equals("getY")
                                    && desc.equals("()I")) {

                                super.visitMethodInsn(opcode, owner, methodName, desc, itf);
                                // Y → Array-Index
                                super.visitIntInsn(Opcodes.SIPUSH, MIN_Y);
                                super.visitInsn(Opcodes.ISUB);
                                return;
                            }
                            super.visitMethodInsn(opcode, owner, methodName, desc, itf);
                        }
                    };
                }

                // 3️⃣ Optional: World.getHeight patchen
                if (name.equals("getHeight") || name.equals("func_175645_m")) {
                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        @Override
                        public void visitInsn(int opcode) {
                            if (opcode == Opcodes.IRETURN) {
                                super.visitIntInsn(Opcodes.SIPUSH, MIN_Y);
                                super.visitInsn(Opcodes.IADD);
                            }
                            super.visitInsn(opcode);
                        }
                    };
                }

                return mv;
            }
        };

        reader.accept(visitor, 0);
        return writer.toByteArray();
    }
}
