package com.github.aetherialmist.dnd.core.spells.service;

import org.yaml.snakeyaml.DumperOptions;

public class YamlDumpOptions extends DumperOptions {

    private static YamlDumpOptions instance;

    public static YamlDumpOptions getInstance() {
        if (instance == null) {
            instance = new YamlDumpOptions();
        }
        return instance;
    }

    public YamlDumpOptions() {
        super();
        this.setIndent(2);
        this.setPrettyFlow(true);
        this.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    }

}
