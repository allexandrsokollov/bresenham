module ru.sokolov.graphics_task_2_8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens ru.sokolov.graphics_task_2_8 to javafx.fxml;
    exports ru.sokolov.graphics_task_2_8;
}