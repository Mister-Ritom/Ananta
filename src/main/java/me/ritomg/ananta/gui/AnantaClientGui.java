package me.ritomg.ananta.gui;

import com.lukflug.panelstudio.base.*;
import com.lukflug.panelstudio.component.*;
import com.lukflug.panelstudio.container.GUI;
import com.lukflug.panelstudio.container.IContainer;
import com.lukflug.panelstudio.layout.*;
import com.lukflug.panelstudio.mc12.MinecraftGUI;
import com.lukflug.panelstudio.popup.*;
import com.lukflug.panelstudio.setting.*;
import com.lukflug.panelstudio.theme.*;
import com.lukflug.panelstudio.widget.ColorPickerComponent;
import me.ritomg.ananta.module.Category;
import me.ritomg.ananta.module.Module;
import me.ritomg.ananta.module.ModuleManager;
import me.ritomg.ananta.module.modules.client.ClickGui;
import me.ritomg.ananta.module.modules.client.CustomFont;
import me.ritomg.ananta.module.modules.theme.GamesenseThemeModule;
import me.ritomg.ananta.module.modules.theme.WindowsTheme;
import me.ritomg.ananta.setting.Setting;
import me.ritomg.ananta.setting.settings.*;
import me.ritomg.ananta.util.font.FontUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class AnantaClientGui extends MinecraftGUI {

    public GUIInterface guiInterface;
    public GUI gui;
    public ClickGui clickGui = ModuleManager.getModule(ClickGui.class);
    public IClient client;
    public int FONT_HEIGHT = 9;
    private ITheme gameSenseTheme;
    private ITheme clearTheme;
    private ITheme rainbowTheme;
    private ITheme windowstheme;
    private ITheme cleargradientTheme;
    private ITheme impacttheme;

    public AnantaClientGui() {
        guiInterface = new GUIInterface(true) {

            @Override
            public void drawString(Point pos, int height, String s, Color c) {
                GlStateManager.pushMatrix();
                GlStateManager.translate(pos.x,pos.y,0);
                double scale=height/(double)(FontUtil.getFontHeight()+(ModuleManager.getModule(CustomFont.class).isEnabled()?1:0));
//                GlStateManager.scale(scale,scale,1);
                end(false);
                if (clickGui.shadow.isOn())
                FontUtil.drawStringWithShadow(s,0,0,c);
                else FontUtil.drawString(s,0,0,c);
                begin(false);
                GlStateManager.scale(scale,scale,1);
                GlStateManager.popMatrix();
            }

            @Override
            public int getFontWidth(int height, String s) {
                double scale=height/(double)(FontUtil.getFontHeight()+(ModuleManager.getModule(CustomFont.class).isEnabled()?1:0));
                return (int)Math.round(FontUtil.getStringWidth(ModuleManager.getModule(CustomFont.class).isEnabled(),s)*scale);
            }

            @Override
            protected String getResourcePrefix() {
                return "ananta:gui/";
            }
        };
        gameSenseTheme = new GameSenseTheme(new AColorScheme(ModuleManager.getModule(GamesenseThemeModule.class)), FONT_HEIGHT, 3, 5, ": " + TextFormatting.GRAY);
        clearTheme = new ClearTheme(new AColorScheme(ModuleManager.getModule(me.ritomg.ananta.module.modules.theme.ClearTheme.class)), () -> false, FONT_HEIGHT, 3, 1, ": " + TextFormatting.GRAY);
        cleargradientTheme = new ClearTheme(new AColorScheme(ModuleManager.getModule(me.ritomg.ananta.module.modules.theme.ClearTheme.class)), () -> true, FONT_HEIGHT, 3, 1, ": " + TextFormatting.GRAY);
        rainbowTheme = new RainbowTheme(new AColorScheme(ModuleManager.getModule(me.ritomg.ananta.module.modules.theme.RainbowTheme.class)), () -> ModuleManager.getModule(me.ritomg.ananta.module.modules.theme.RainbowTheme.class).ignoreDisabled.isOn(), () -> ModuleManager.getModule(me.ritomg.ananta.module.modules.theme.RainbowTheme.class).buttonRainbow.isOn(), () -> 1, FONT_HEIGHT, 3, ":" + TextFormatting.GRAY);
        windowstheme = new Windows31Theme(new AColorScheme(ModuleManager.getModule(WindowsTheme.class)), FONT_HEIGHT, 3, 5, ":" + TextFormatting.GRAY);
        impacttheme = new ImpactTheme(new AColorScheme(ModuleManager.getModule(me.ritomg.ananta.module.modules.theme.ImpactTheme.class)), FONT_HEIGHT, 3);

        ITheme theme = getTheme();

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
                    public String getDescription() {
                        return module.getDescription();
                    }

                    @Override
                    public Stream<ISetting<?>> getSettings() {

                        Stream<ISetting<?>> temp=module.getSettings().stream().map(AnantaClientGui.this::createSettings);
                        final Stream<ISetting<?>> concat = Stream.concat(temp, Stream.concat(Stream.of(new IBooleanSetting() {
                            @Override
                            public String getDisplayName() {
                                return "ToggleMessage";
                            }

                            @Override
                            public void toggle() {
                                module.toggleMessage = !module.toggleMessage;
                            }

                            @Override
                            public boolean isOn() {
                                return module.toggleMessage;
                            }
                        }), Stream.of(new IKeybindSetting() {
                            @Override
                            public String getDisplayName() {
                                return "Bind";
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
                        return concat;
                    }

                    @Override
                    public String getDisplayName() {
                        return module.getName();
                    }
                });
            }
        });
        gui = new GUI(guiInterface, theme.getDescriptionRenderer(), new MousePositioner(new Point(10, 10)));

        Supplier<Animation> animation=()->new SettingsAnimation(()->clickGui.animationSpeed.getCurrent(),()->guiInterface.getTime());

        BiFunction<Context,Integer,Integer> scrollHeight=(context, componentHeight)->{
            if (clickGui.scrolling.is("Screen")) return componentHeight;
            else return Math.min(componentHeight,Math.max(10*4, AnantaClientGui.this.height-context.getPos().y-10));
        };

        IContainer<IFixedComponent> container = new IContainer<IFixedComponent>() {
            @Override
            public boolean addComponent(IFixedComponent component) {
                return gui.addComponent(new IFixedComponentProxy<IFixedComponent>() {

                    @Override
                    public void handleScroll (Context context, int diff) {
                        IFixedComponentProxy.super.handleScroll(context,diff);
                        if (clickGui.scrolling.is("Screen")) {
                            Point p = getPosition(guiInterface);
                            p.translate(0, -diff);
                            setPosition(guiInterface, p);
                        }
                    }

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
                    public void handleScroll (Context context, int diff) {
                        IFixedComponentProxy.super.handleScroll(context,diff);
                        if (clickGui.scrolling.is("Screen")) {
                            Point p = getPosition(guiInterface);
                            p.translate(0, -diff);
                            setPosition(guiInterface, p);
                        }
                    }

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
                    return new ColorPickerComponent(setting,theme);
            }
        };

        PopupTuple popupTuple=new PopupTuple(new PanelPositioner(new Point(0,0)),true,new IScrollSize() {
            @Override
            public int getScrollHeight (Context context, int componentHeight) {
                return scrollHeight.apply(context,componentHeight);
            }
        });

        //normal layout
        IComponentAdder classicPanelaAder = new PanelAdder(container,false,()->clickGui.layout.is("normal"),title->title) {
            @Override
            protected IScrollSize getScrollSize (IResizable size) {
                return new IScrollSize() {
                    @Override
                    public int getScrollHeight (Context context, int componentHeight) {
                        return scrollHeight.apply(context,componentHeight);
                    }
                };
            }

            @Override
            protected IResizable getResizable(int width) {

                Dimension dimension = new Dimension(130,100 );

                return new IResizable() {
                    @Override
                    public Dimension getSize() {
                        return dimension;
                    }

                    @Override
                    public void setSize(Dimension size) {

                        if (size.getWidth() < 75) size.width = 75;
                        if (size.getHeight() <50) size.height = 50;
                         dimension.setSize(size);
                    }
                };
            }
        };
        int WIDTH = 100, HEIGHT = 12, DISTANCE = 10;
        ILayout classicPanelLayout=new PanelLayout(100,new Point(10,10),(110)/2,112,animation, level-> ChildUtil.ChildMode.DOWN, level-> ChildUtil.ChildMode.DOWN,popupTuple);
        classicPanelLayout.populateGUI(classicPanelaAder,generator,client,theme);

        //stacked panel layout
        IComponentAdder satckedComponentAdder = new StackedPanelAdder(container,new Labeled("AnantaClient",null,()->true),theme, new Point(0,0), WIDTH + 400,animation, ChildUtil.ChildMode.DOWN,new PanelPositioner(new Point(0,0)),()->clickGui.layout.is("Stacked"),"Stacked") {
            @Override
            protected IResizable getResizable(int width) {

                Dimension dimension = new Dimension(500,100);

                return new IResizable() {
                    @Override
                    public Dimension getSize() {
                        return dimension;
                    }

                    @Override
                    public void setSize(Dimension size) {

                        if (size.getWidth() < 75) size.width = 75;
                        if (size.getHeight() <50) size.height = 50;
                        dimension.setSize(size);
                    }
                };
            }
        };
        ILayout StackedPanelLayout=new PanelLayout(WIDTH,new Point(DISTANCE,DISTANCE),(WIDTH+DISTANCE)/2,HEIGHT+DISTANCE,animation,level-> ChildUtil.ChildMode.DOWN, level-> ChildUtil.ChildMode.DOWN,popupTuple);
        StackedPanelLayout.populateGUI(satckedComponentAdder,generator,client, theme);
        //single panel layout

        IComponentAdder singlePanelAdder = new SinglePanelAdder(container, new Labeled("AnantaClient",null, ()->true), theme,new Point(0,0), WIDTH + 500,animation, ()->clickGui.layout.is("Single"), "SinglePanel") {
            @Override
            protected IResizable getResizable(int width) {

                Dimension dimension = new Dimension(500,100 );

                return new IResizable() {
                    @Override
                    public Dimension getSize() {
                        return dimension;
                    }

                    @Override
                    public void setSize(Dimension size) {

                        if (size.getWidth() < 75) size.width = 75;
                        if (size.getHeight() <50) size.height = 50;
                        dimension.setSize(size);
                    }
                };
            }
        };

        ILayout singlePanelLayout=new PanelLayout(WIDTH,new Point(DISTANCE,DISTANCE),(WIDTH+DISTANCE)/2,HEIGHT+DISTANCE,animation,level-> ChildUtil.ChildMode.DOWN, level-> ChildUtil.ChildMode.DOWN,popupTuple);
        singlePanelLayout.populateGUI(singlePanelAdder,generator,client, theme);

        // CSGO Layout!
        IComponentAdder horizontalCSGOAdder=new PanelAdder(gui,true,()->clickGui.layout.is("CSGO"),title->title);
        ILayout horizontalCSGOLayout=new CSGOLayout(new Labeled("AnantaClient",null,()->true),new Point(100,100),470,WIDTH,animation,"Enabled",true,true,2, ChildUtil.ChildMode.DOWN,popupTuple) {
            @Override
            public int getScrollHeight (Context context, int componentHeight) {
                return 320;
            }

            @Override
            protected boolean isUpKey (int key) {
                return key==Keyboard.KEY_UP;
            }

            @Override
            protected boolean isDownKey (int key) {
                return key==Keyboard.KEY_DOWN;
            }

            @Override
            protected boolean isLeftKey (int key) {
                return key==Keyboard.KEY_LEFT;
            }

            @Override
            protected boolean isRightKey (int key) {
                return key==Keyboard.KEY_RIGHT;
            }
        };
        horizontalCSGOLayout.populateGUI(horizontalCSGOAdder,generator,client, theme);

        //SEARCHABLE Layout!
        Comparator<IModule> modulesCompar = Comparator.comparing(o -> o.getDisplayName().toLowerCase());
        IComponentAdder searchAdder=new PanelAdder(gui,true,()->clickGui.layout.is("Search"),title->title);
        ILayout searchLayout = new SearchableLayout(new Labeled("AnantaClient", null, () -> true), new Labeled("Search", null, () -> true), new Point(100, 100), 480, WIDTH, animation, "Enabled", 2, ChildUtil.ChildMode.DOWN, popupTuple, modulesCompar , character -> character >= ' ', new TextFieldKeys());
        searchLayout.populateGUI(searchAdder, generator,client, theme);

    }

    // see this custom code lol
    @Override
    public void onGuiClosed() {
        ModuleManager.getModule(ClickGui.class).disable();
        super.onGuiClosed();
    }

    //settings
    private ISetting<?> createSettings (Setting setting) {
        if (setting instanceof BooleanSetting) {
            return new IBooleanSetting() {
                @Override
                public String getDescription() {
                    return setting.getDescription();
                }
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
                public String getDescription() {
                    return setting.getDescription();
                }
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
                public String getDescription() {
                    return setting.getDescription();
                }
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
                @Override
                public String getDescription() {
                    return setting.getDescription();
                }
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
                    ((ModeSetting)setting).decreaseMode();
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
                public String getDescription() {
                    return setting.getDescription();
                }

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

    public ITheme getTheme() {
        return new IThemeMultiplexer() {
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
    protected GUI getGUI() {
        return gui;
    }
}
