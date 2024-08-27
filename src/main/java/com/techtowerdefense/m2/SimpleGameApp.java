package com.techtowerdefense.m2;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.techtowerdefense.m2.components.EnemyComponent;
import com.techtowerdefense.m2.components.TowerComponent;
import com.techtowerdefense.m2.data.*;
import com.techtowerdefense.m2.ui.GameMenuFactory;
import com.techtowerdefense.m2.ui.TowerMenu;
import com.techtowerdefense.m2.ui.TowerSelectionBox;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getWorldProperties;
import static com.techtowerdefense.m2.EntityType.WAY;

public class SimpleGameApp extends GameApplication {


    // GameDataController singleton
    private static GameDataController gdcsingleton = GameDataController.getInstance();

    private WaveData waveData = new WaveData(1, "enemy1.json", 15, "way1", 250);
    private WaveData bossWave = new WaveData(1, "enemy2.json", 1, "way1", 250);
    private int enemiesKilled = 0;
    private TowerSelectionBox towerSelectionBox;
    private TowerSelectionBox upgradeSelection;
    private List<TowerData> towerData;
    private List<TowerData> upgradeData;

    private Button startButton;

    private Runnable waveStartAction = EmptyRunnable.INSTANCE;

    private static Rectangle2D worldBounds =
            new Rectangle2D(0, 0, gdcsingleton.getappwidth(), gdcsingleton.getappheight());



    @Override
    protected void initSettings(GameSettings settings) {
        //game setting general
        settings.setTitle("TechTowerDefense");
        settings.setMainMenuEnabled(true);
        settings.setVersion("0.3");

        settings.setWidth(32 * 64);
        settings.setHeight(16 * 64);
        settings.setApplicationMode(ApplicationMode.DEVELOPER);
        settings.setSceneFactory(new GameMenuFactory());

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        // set initialized variables
        vars.put("health", gdcsingleton.getHealth());
        vars.put("money", gdcsingleton.getMoney());
    }

    @Override
    protected void initGame() {

        loadTowerData();
        loadUpgradesData();

        getGameWorld().addEntityFactory(new TowerDefenseFactory());

        setLevelFromMap("tmx/citymap.tmx");

        FXGL.getWorldProperties().<Integer>addListener("health", (old, newValue) -> {
            if (newValue <= 0) {
                gameOver();
            }
        });

        towerSelectionBox = new TowerSelectionBox(towerData);
        upgradeSelection = new TowerSelectionBox(upgradeData);

        startButton = new Button("Start");
        startButton.setOnAction(e -> {
            nextWave();
        });

        loadCurrentLevel();
    }

    private void loadCurrentLevel() {
        System.out.println("Loads Level");
        getGameWorld().getEntitiesFiltered(e -> e.isType("TiledMapLayer"))
                .forEach(e -> {
                    e.getViewComponent().addOnClickHandler(event -> {
                        towerSelectionBox.setVisible(false);
                    });
                });
        scheduleNextWave();
    }

    private void scheduleNextWave() {
        System.out.println("schedules wave");
        scheduleWave(() -> nextWave());
    }

    private void nextWave() {
        System.out.println("Button Clicked To Spawn Wave");
        spawnWave(waveData);

    }

    private void spawnWave(WaveData wave) {
        System.out.println("Spawning Wave");
        var wayEntity = getGameWorld().getSingleton(e ->
                e.isType(WAY) && e.getString("name").equals(wave.way())
        );
        EnemyData data = getAssetLoader().loadJSON("enemies/"
                + wave.enemy(), EnemyData.class).get();

        Polygon p = wayEntity.getObject("polygon");
        var way = Way.fromPolygon(p, wayEntity.getX(), wayEntity.getY());

        for (int i = 0; i < wave.amount(); i++) {
            runOnce(() -> {
                spawnWithScale(
                        "Enemy",
                        new SpawnData()
                                .put("way", way)
                                .put("enemyData", data),
                        Duration.seconds(0.45),
                        Interpolators.ELASTIC.EASE_OUT()
                );

            }, Duration.seconds(i * data.interval()));
        }
    }

    private void loadTowerData() {
        List<String> towerNames = List.of(
                "tower1.json",
                "tower2.json",
                "tower3.json"
        );

        towerData = towerNames.stream()
                .map(name -> getAssetLoader().loadJSON("towers/" + name, TowerData.class).get())
                .toList();
    }

    private void loadUpgradesData() {
        List<String> towerNames = List.of(
                "tower4.json"
        );
        upgradeData = towerNames.stream()
                .map(name -> getAssetLoader().loadJSON("towers/" + name, TowerData.class).get())
                .toList();
    }
    private void loadUpgradesData(Entity cell) {
            List<String> towerNames = new ArrayList<>();
            Entity e = cell.getProperties().getValue("tower");
            if (e.getComponent(TowerComponent.class).getData().name().equals("Tower 1")) {
                towerNames.add("tower4.json");
            }
            if (e.getComponent(TowerComponent.class).getData().name().equals("Tower 2")) {
                towerNames.add("tower5.json");
            }
            if (e.getComponent(TowerComponent.class).getData().name().equals("Tower 3")) {
                towerNames.add("tower6.json");
            }
            System.out.println(e.getComponent(TowerComponent.class).getData().name());
            upgradeData = towerNames.stream()
                    .map(name -> getAssetLoader().loadJSON("towers/" + name, TowerData.class).get())
                    .toList();

    }

    @Override
    protected void initUI() {
        towerSelectionBox.setVisible(false);
        addUINode(towerSelectionBox);

        upgradeSelection.setVisible(false);
        addUINode(upgradeSelection);

        // set health content
        Text textHealth = new Text();
        textHealth.textProperty().bind(getWorldProperties()
                .intProperty("health").asString());
        Label health = new Label("Health:");
        health.setStyle("-fx-text-fill: white; -fx-font-size: 19px;");
        textHealth.setStyle("-fx-text-fill: white; -fx-font-size: 19px;");
        HBox healthinfo = new HBox(3, health, textHealth);

        // set money content
        Text textMoney = new Text();
        textMoney.textProperty().bind(getWorldProperties()
                .intProperty("money").asString());
        Label money = new Label("Money:");
        money.setStyle("-fx-text-fill: white; -fx-font-size: 19px;");
        textMoney.setStyle("-fx-text-fill: white; -fx-font-size: 19px;");
        HBox moneyinfo = new HBox(3, money, textMoney);

        // show info
        VBox info = new VBox(3, healthinfo, moneyinfo, startButton);
        info.setTranslateY(getAppWidth() / 2.0 - 1010);
        info.setTranslateX(getAppWidth() / 2.0 + 915);
        getGameScene().addUINode(info);



        // show towermenu
        TowerMenu towermenu = new TowerMenu(250, 600);
        towermenu.setTranslateX(getAppWidth() / 2.0 + 895);
        towermenu.setTranslateY(getAppWidth() / 2.0 - 750);
        getGameScene().addUINode(towermenu);
    }

    @Override
    protected void initInput() {
        Input input = FXGL.getInput();
        TowerManager towerManager = TowerManager.INSTANCE;

//        input.addAction(new UserAction("Place Tower") {
//            @Override
//            protected void onActionBegin() {
//                var mousePos = input.getMousePositionWorld();
//
//                if (worldBounds.contains(mousePos)) {
//                    var status = towerManager.placeTower(mousePos);
//
//
////                    set("money", gdcsingleton.getMoney());
//                }
//            }
//        }, MouseButton.PRIMARY);

    }
    public void onCellClicked(Entity cell) {
        // if we already have a tower on this tower base, ignore call
        if (cell.getProperties().exists("tower")){
            loadUpgradesData(cell);
            upgradeSelection.changeList(upgradeData);

            upgradeSelection.setCell(cell);
            upgradeSelection.setVisible(true);

            var x = cell.getX() > getAppWidth() / 2.0 ? cell.getX() - 250 : cell.getX();

            upgradeSelection.setTranslateX(x);
            upgradeSelection.setTranslateY(cell.getY());
        }
        else {
            towerSelectionBox.setCell(cell);
            towerSelectionBox.setVisible(true);

            var x = cell.getX() > getAppWidth() / 2.0 ? cell.getX() - 250 : cell.getX();

            towerSelectionBox.setTranslateX(x);
            towerSelectionBox.setTranslateY(cell.getY());
        }
    }

    public void onTowerSelected(Entity cell, TowerData data) {
        if (geti("money") - data.cost()*gdcsingleton.getPriceFact() >= 0) {
            towerSelectionBox.setVisible(false);
            upgradeSelection.setVisible(false);

            inc("money", -data.cost()*gdcsingleton.getPriceFact());

            var tower = spawnWithScale(
                    "Tower",
                    new SpawnData(cell.getPosition()).put("towerData", data),
                    Duration.seconds(0.85),
                    Interpolators.ELASTIC.EASE_OUT()
            );
            if (cell.getProperties().exists("tower")) {
                Entity e = cell.getProperties().getValue("tower");
                e.removeFromWorld();
            }
            cell.setProperty("tower", tower);
        }
    }

    private void gameOver() {
        showMessage("GAME OVER! You lost king. Wanna try again?",
                getGameController()::gotoMainMenu);
    }
    private void winGame() {
        showMessage("You win king, I'm so proud. Want play again?",
                getGameController()::gotoMainMenu);
    }
    public void onEnemyKilled(Entity enemy) {
        inc("money", enemy.getComponent(EnemyComponent.class).getData().reward());
        enemiesKilled += 1;
        if (enemiesKilled == 15) {
            spawnWave(bossWave);
        }
        if(enemy.getComponent(EnemyComponent.class).getData().reward() == 1000) {
            winGame();
        }
    }

    public void onEnemyReachedEnd(Entity enemy) {
        inc("health", -enemy.getComponent(EnemyComponent.class).getData().damage());
    }

    public void scheduleWave(Runnable action) {
        System.out.println("wave scheduled");
        this.waveStartAction = action;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
