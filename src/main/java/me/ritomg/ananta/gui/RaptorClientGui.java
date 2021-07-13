package me.ritomg.ananta.gui;

import com.lukflug.panelstudio.base.*;
import com.lukflug.panelstudio.component.IComponent;
import com.lukflug.panelstudio.component.IFixedComponent;
import com.lukflug.panelstudio.component.IFixedComponentProxy;
import com.lukflug.panelstudio.component.IScrollSize;
import com.lukflug.panelstudio.container.IContainer;
import com.lukflug.panelstudio.hud.HUDGUI;
import com.lukflug.panelstudio.layout.*;
import com.lukflug.panelstudio.mc12.MinecraftHUDGUI;
import com.lukflug.panelstudio.popup.IPopupPositioner;
import com.lukflug.panelstudio.popup.MousePositioner;
import com.lukflug.panelstudio.popup.PanelPositioner;
import com.lukflug.panelstudio.popup.PopupTuple;
import com.lukflug.panelstudio.setting.*;
import com.lukflug.panelstudio.theme.*;
import com.lukflug.panelstudio.widget.ColorPickerComponent;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.module.modules.client.ClickGui;
import me.ritomg.ananta.setting.Setting;
import me.ritomg.ananta.setting.settings.*;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class RaptorClientGui extends MinecraftHUDGUI {

    public GUIInterface guiInterface;
    public HUDGUI gui;
    public ClickGui clickGui = ModuleManager.getModule(ClickGui.class);
    public IClient client;
    public int FONT_HEIGHT = 9;
    private ITheme gameSenseTheme;
    private ITheme clearTheme;
    private ITheme rainbowTheme;
    private ITheme windowstheme;
    private ITheme cleargradientTheme;
    private ITheme impacttheme;

    public RaptorClientGui() {
        guiInterface = new GUIInterface(true) {
            @Override
            protected String getResourcePrefix() {
                return "ananta:gui/";
            }
        };
        gameSenseTheme = new GameSenseTheme(new RCColorScheme(clickGui.theme.is("GamesenseTheme")), FONT_HEIGHT, 3, 5, ": " + TextFormatting.GRAY);
        clearTheme = new ClearTheme(new RCColorScheme(clickGui.theme.is("ClearTheme")), () -> false, FONT_HEIGHT, 3, 1, ": " + TextFormatting.GRAY);
        cleargradientTheme = new ClearTheme(new RCColorScheme(clickGui.theme.is("ClearGradientTheme")), () -> true, FONT_HEIGHT, 3, 1, ": " + TextFormatting.GRAY);
        rainbowTheme = new RainbowTheme(new RCColorScheme(clickGui.theme.is("RainbowTheme")), () -> !clickGui.ignoreDisabled.isOn(), () -> clickGui.buttonRainbow.isOn(), () -> 1, FONT_HEIGHT, 3, ":" + TextFormatting.GRAY);
        windowstheme = new Windows31Theme(new RCColorScheme(clickGui.theme.is("Windows")), FONT_HEIGHT, 3, 5, ":" + TextFormatting.GRAY);
        impacttheme = new ImpactTheme(new RCColorScheme(clickGui.theme.is("ImpactTheme")), FONT_HEIGHT, 3);
        ITheme theme = new IThemeMultiplexer() {
            @Override
            public ITheme getTheme() {
                switch (clickGui.theme.getCurrentMode()) {
                    case "Windows":
                        return windowstheme;
                    case "RainbowTheme":
                        return rainbowTheme;
                    case "ClearTheme":
                        return clearTheme;
                    case "ClearGradientTheme":
                        return cleargradientTheme;
                    case "ImpactTheme":
                        return impacttheme;
                    default:
                        return gameSenseTheme;
                }
            }
        };

        client = () -> Arrays.stream(Category.values()).sorted(Comparator.comparing(Enum::toString)).map(category -> new ICategory() {

            @Override
            public String getDisplayName() {
                return category.toString();
            }

            @Override
            public Stream<IModule> getModules() {
                return ModuleManager.getModulesinCategory(category).stream().sorted(Comparator.comparing(Module::getName)).map(module -> new IModule() {
                    @Override
                    public IToggleable isEnabled() {
                        return new IToggleable() {
                            @Override
                            public void toggle() {
                                module.toggle();
                            }

                            @Override
                            public boolean isOn() {
                                return module.isEnabled();
                            }
                        };
                    }

                    @Override
                    public Stream<ISetting<?>> getSettings() {
                        Stream<ISetting<?>> temp=module.getSettings().stream().map(RaptorClientGui.this::createSettings);
                        return Stream.concat(temp,Stream.concat(Stream.of(new IBooleanSetting() {
                            @Override
                            public String getDisplayName() {
                                return "Toggle Msgs";
                            }

                            @Override
                            public void toggle() {
                            }

                            @Override
                            public boolean isOn() {
                                return false;
                            }
                        }),Stream.of(new IKeybindSetting() {
                            @Override
                            public String getDisplayName() {
                                return "Keybind";
                            }

                            @Override
                            public int getKey() {
                                return module.getBind();
                            }

                            @Override
                            public void setKey(int key) {
                                module.setBind(key);
                            }

                            @Override
                            public String getKeyName() {
                                return Keyboard.getKeyName(module.getBind());
                            }
                        })));
                    }

                    @Override
                    public String getDisplayName() {
                        return module.getName();
                    }
                });
            }
        });

        IToggleable guiToggle = new SimpleToggleable(false);
        IToggleable hudToggle = new SimpleToggleable(false);

        gui = new HUDGUI(guiInterface, theme.getDescriptionRenderer(), new MousePositioner(new Point(10, 10)), guiToggle, hudToggle);

        Supplier<Animation> animation=()->new SettingsAnimation(()->clickGui.animationSpeed.getCurrent(),()->guiInterface.getTime());

        IContainer<IFixedComponent> container = new IContainer<IFixedComponent>() {
            @Override
            public boolean addComponent(IFixedComponent component) {
                return gui.addComponent(new IFixedComponentProxy<IFixedComponent>() {

                    @Override
                    public IFixedComponent getComponent() {
                        return component;
                    }
                });
            }

            @Override
            public boolean addComponent(IFixedComponent component, IBoolean visible) {
                return gui.addComponent(new IFixedComponentProxy<IFixedComponent>() {
                    @Override
                    public IFixedComponent getComponent() {
                        return component;
                    }
                },visible);
            }

            @Override
            public boolean removeComponent(IFixedComponent component) {
                return gui.removeComponent(component);
            }
        };

        IComponentGenerator generator=new ComponentGenerator(scancode->scancode==Keyboard.KEY_DELETE, character->character>=' ', new TextFieldKeys()){
            @Override
            public IComponent getColorComponent(IColorSetting setting, Supplier<Animation> animation, IComponentAdder adder, ThemeTuple theme, int colorLevel, boolean isContainer) {
                return new ColorPickerComponent(setting,new ThemeTuple(theme.theme,theme.logicalLevel,colorLevel));
            }
        };

        PopupTuple popupTuple=new PopupTuple(new PanelPositioner(new Point(0,0)),false,new IScrollSize(){});

        //normal layout
        IComponentAdder classicPanelaAder = new PanelAdder(container,false,()->clickGui.layout.is("normal"),title->title);
        ILayout classicPanelLayout=new PanelLayout(100,new Point(10,10),(110)/2,112,animation, level-> ChildUtil.ChildMode.DOWN, level-> ChildUtil.ChildMode.DOWN,popupTuple);
        classicPanelLayout.populateGUI(classicPanelaAder,generator,client,theme);

    }

    private ISetting<?> createSettings (Setting setting) {
        if (setting instanceof BooleanSetting) {
            return new IBooleanSetting() {
                @Override
                public String getDisplayName() {
                    return setting.getName();
                }

                @Override
                public IBoolean isVisible() {
                    return setting::getIsVisible;
                }

                @Override
                public void toggle() {
                    ((BooleanSetting)setting).On(!((BooleanSetting)setting).isOn());
                }

                @Override
                public boolean isOn() {
                    return ((BooleanSetting)setting).isOn();
                }

                @Override
                public Stream<ISetting<?>> getSubSettings() {
                    return null;
                }
            };
        } else if (setting instanceof NumberSetting) {
            return new INumberSetting() {
                @Override
                public String getDisplayName() {
                    return setting.getName();
                }

                @Override
                public IBoolean isVisible() {
                    return setting::getIsVisible;
                }

                @Override
                public double getNumber() {
                    return ((NumberSetting)setting).getCurrent();
                }

                @Override
                public void setNumber(double value) {
                    ((NumberSetting)setting).setCurrent((int)Math.round(value));
                }

                @Override
                public double getMaximumValue() {
                    return ((NumberSetting)setting).getMax();
                }

                @Override
                public double getMinimumValue() {
                    return ((NumberSetting)setting).getMin();
                }

                @Override
                public int getPrecision() {
                    return 0;
                }

                @Override
                public Stream<ISetting<?>> getSubSettings() {
                    return null;
                }
            };
        } else if (setting instanceof DNumberSetting) {
            return new INumberSetting() {
                @Override
                public String getDisplayName() {
                    return setting.getName();
                }

                @Override
                public IBoolean isVisible() {
                    return setting::getIsVisible;
                }

                @Override
                public double getNumber() {
                    return ((DNumberSetting)setting).getCurrent();
                }

                @Override
                public void setNumber(double value) {
                    ((DNumberSetting)setting).setCurrent(value);
                }

                @Override
                public double getMaximumValue() {
                    return ((DNumberSetting)setting).getMax();
                }

                @Override
                public double getMinimumValue() {
                    return ((DNumberSetting)setting).getMin();
                }

                @Override
                public int getPrecision() {
                    return 2;
                }

                @Override
                public Stream<ISetting<?>> getSubSettings() {
                    return null;
                }
            };
        } else if (setting instanceof ModeSetting) {
            return new IEnumSetting() {
                private final ILabeled[] states = ((ModeSetting) setting).getModes().stream().map(mode -> new Labeled(mode, null, () -> true)).toArray(ILabeled[]::new);

                @Override
                public String getDisplayName() {
                    return setting.getName();
                }

                @Override
                public IBoolean isVisible() {
                    return setting::getIsVisible;
                }

                @Override
                public void increment() {
                    ((ModeSetting) setting).increaseMode();
                }

                @Override
                public void decrement() {

                }

                @Override
                public String getValueName() {
                    return ((ModeSetting) setting).getCurrentMode();
                }

                @Override
                public int getValueIndex() {
                    return ((ModeSetting) setting).getModes().indexOf(getValueName());
                }

                @Override
                public void setValueIndex(int index) {
                    ((ModeSetting) setting).setCurrentMode(((ModeSetting) setting).getModes().get(index));
                }

                @Override
                public ILabeled[] getAllowedValues() {
                    return states;
                }

                @Override
                public Stream<ISetting<?>> getSubSettings() {
                    return null;
                }
            };
        } else if (setting instanceof ColourSetting) {
            return new IColorSetting() {
                @Override
                public String getDisplayName() {
                    return TextFormatting.BOLD+setting.getName();
                }

                @Override
                public IBoolean isVisible() {
                    return setting::getIsVisible;
                }

                @Override
                public Color getValue() {
                    return ((ColourSetting)setting).getColor();
                }

                @Override
                public void setValue(Color value) {
                    ((ColourSetting)setting).setColor(value);
                }

                @Override
                public Color getColor() {
                    return ((ColourSetting)setting).getColor();
                }

                @Override
                public boolean getRainbow() {
                    return ((ColourSetting)setting).isRainbow();
                }

                @Override
                public void setRainbow(boolean rainbow) {
                    ((ColourSetting)setting).setRainbow(rainbow);
                }

                @Override
                public boolean hasAlpha() {
                    return ((ColourSetting)setting).isHasAlpha();
                }

                @Override
                public boolean allowsRainbow() {
                    return ((ColourSetting)setting).isAllowRainbow();
                }

                @Override
                public boolean hasHSBModel() {
                    return true;
                }
            };
        } else if (setting instanceof StringSetting) {
            return new IStringSetting() {
                @Override
                public String getValue() {
                    return ((StringSetting) setting).getText();
                }

                @Override
                public void setValue(String string) {
                    ((StringSetting) setting).setText(string);
                }

                @Override
                public String getDisplayName() {
                    return setting.getName();
                }
            };
        }
        return new ISetting<Void>() {
            @Override
            public String getDisplayName() {
                return setting.getName();
            }

            @Override
            public IBoolean isVisible() {
                return setting::getIsVisible;
            }

            @Override
            public Void getSettingState() {
                return null;
            }

            @Override
            public Class<Void> getSettingClass() {
                return Void.class;
            }

            @Override
            public Stream<ISetting<?>> getSubSettings() {
                return null;
            }
        };
    }

    @Override
    protected GUIInterface getInterface() {
        return guiInterface;
    }

    @Override
    protected int getScrollSpeed() {
        return clickGui.scrollSpeed.getCurrent();
    }

    @Override
    protected HUDGUI getGUI() {
        return gui;
    }
}
