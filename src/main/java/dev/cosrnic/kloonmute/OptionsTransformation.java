package dev.cosrnic.kloonmute;

import felis.transformer.ClassContainer;
import felis.transformer.Transformation;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldNode;

public class OptionsTransformation implements Transformation {
    @Override
    public void transform(@NotNull ClassContainer container) {
        FieldNode field = container.getNode().fields.stream().filter(fieldNode -> fieldNode.name.equals("keyMappings")).findFirst().orElse(null);

        if (field == null) {
            Mod.LOGGER.error("keyMappings field is null!");
            return;
        }

        field.access = field.access & ~Opcodes.ACC_FINAL;

    }
}
