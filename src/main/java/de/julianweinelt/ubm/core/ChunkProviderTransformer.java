package de.julianweinelt.ubm.core;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

public class ChunkProviderTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        // Forge 1.12.2: ChunkProviderServer
        if ("net.minecraft.world.gen.ChunkProviderServer".equals(transformedName)) {
            System.out.println("[Coremod] Patching ChunkProviderServer...");
            return patchChunkProvider(basicClass);
        }
        return basicClass;
    }

    private byte[] patchChunkProvider(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        ClassVisitor visitor = new ClassVisitor(Opcodes.ASM5, writer) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

                // Patch provideChunk(int x, int z)
                if ((name.equals("provideChunk") || name.equals("func_73158_c")) && desc.equals("(II)Lnet/minecraft/world/chunk/Chunk;")) {
                    System.out.println("[Coremod] Patching provideChunk to return ExtendedChunk...");

                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        @Override
                        public void visitCode() {
                            // Wir ersetzen den ganzen Method Body:
                            // return new ExtendedChunk(this.world, x, z);
                            mv.visitVarInsn(Opcodes.ALOAD, 0); // this
                            mv.visitFieldInsn(Opcodes.GETFIELD, "net/minecraft/world/gen/ChunkProviderServer", "world", "Lnet/minecraft/world/World;"); 
                            mv.visitVarInsn(Opcodes.ILOAD, 1); // x
                            mv.visitVarInsn(Opcodes.ILOAD, 2); // z
                            mv.visitTypeInsn(Opcodes.NEW, "de/julianweinelt/ubm/core/EntendedChunk");
                            mv.visitInsn(Opcodes.DUP);
                            mv.visitVarInsn(Opcodes.ALOAD, 0); // this.world
                            mv.visitVarInsn(Opcodes.ILOAD, 1); // x
                            mv.visitVarInsn(Opcodes.ILOAD, 2); // z
                            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "de/julianweinelt/ubm/core/EntendedChunk", "<init>", "(Lnet/minecraft/world/World;II)V", false);
                            mv.visitInsn(Opcodes.ARETURN);
                        }

                        @Override
                        public void visitMaxs(int maxStack, int maxLocals) {
                            super.visitMaxs(5, 3);
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
