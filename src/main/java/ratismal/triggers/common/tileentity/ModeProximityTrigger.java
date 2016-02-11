package ratismal.triggers.common.tileentity;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Ratismal on 2016-02-10.
 */

public enum ModeProximityTrigger implements IStringSerializable {
    PLAYER(0, "player"),
    MOB(1, "mob"),
    ITEM(2, "item"),
    ENTITY(3, "entity");

    private int id;
    private String name;

    private ModeProximityTrigger(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private static ModeProximityTrigger[] vals = values();
    public ModeProximityTrigger next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }
    public static ModeProximityTrigger get(int number) {
        return vals[number];
    }

    @Override
    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }
}
