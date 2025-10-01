package de.julianweinelt.ubm.core;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

public class ChunkTransformer implements IClassTransformer {
    private static final int OFFSET = 64;

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if ("net.minecraft.world.chunk.Chunk".equals(transformedName)) {
            System.out.println("[Coremod] Patching Chunk class...");
            return patchChunk(basicClass);
        }
        return basicClass;
    }


    private byte[] patchChunk(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

        ClassVisitor visitor = new ClassVisitor(Opcodes.ASM5, writer) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

                if (name.equals("getBlockState") || name.equals("func_177435_g")) {
                    System.out.println("[Coremod] Found getBlockState, patching...");

                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        @Override
                        public void visitMethodInsn(int opcode, String owner, String methodName, String desc, boolean itf) {
                            if (opcode == Opcodes.INVOKEVIRTUAL
                                    && owner.equals("net/minecraft/util/math/BlockPos")
                                    && methodName.equals("getY")
                                    && desc.equals("()I")) {

                                super.visitMethodInsn(opcode, owner, methodName, desc, itf);

                                super.visitIntInsn(Opcodes.SIPUSH, OFFSET);
                                super.visitInsn(Opcodes.IADD);
                                return;
                            }

                            super.visitMethodInsn(opcode, owner, methodName, desc, itf);
                        }
                    };
                }
                //TODO: Patch setBlockState too

                if (name.equals("isYWithinBounds") || name.equals("func_76600_a")) {
                    System.out.println("[Coremod] Found isYWithinBounds, patching...");

                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        @Override
                        public void visitCode() {
                            super.visitCode();
                            mv.visitInsn(Opcodes.ICONST_1);
                            mv.visitInsn(Opcodes.IRETURN);
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