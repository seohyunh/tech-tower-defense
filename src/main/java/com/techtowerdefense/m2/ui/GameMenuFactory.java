package com.techtowerdefense.m2.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.techtowerdefense.m2.ui.scene.GameMainMenu;



public class GameMenuFactory extends SceneFactory {
    @Override
    public FXGLMenu newMainMenu() {
        return new GameMainMenu(MenuType.MAIN_MENU);
    }
}
