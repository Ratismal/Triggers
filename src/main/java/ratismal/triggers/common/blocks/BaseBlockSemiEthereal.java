package ratismal.triggers.common.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.items.ItemEyeTransient;
import ratismal.triggers.common.tileentity.TileSemiEthereal;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Ratismal on 2016-01-13.
 */

public class BaseBlockSemiEthereal extends BaseBlock {
    //Blocks that can be walked through when not wearing the Transient Eye

    //public static final PropertyBool ENABLED = PropertyBool.create("enabled");
    public static final PropertyBool VISIBLE = PropertyBool.create("visible");

    public BaseBlockSemiEthereal(String name) {
        super(name);
        setHardness(3f);
        setBlockUnbreakable();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public EnumWorldBlockLayer getBlockLayer() {
        //    if (TriggersMod.ClientProxy.getThePlayer().inventory.armorItemInSlot(3) != null &&
        //         TriggersMod.ClientProxy.getThePlayer().inventory.armorItemInSlot(3).getItem() instanceof ItemEyeTransient) {
        return EnumWorldBlockLayer.TRANSLUCENT;
        // } else {
        //      return EnumWorldBlockLayer.CUTOUT;
        // }
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isNormalCube() {
        return false;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        if (isNoCol()) {
            return state.withProperty(VISIBLE, true);
        }
        return state.withProperty(VISIBLE, false);
    }

    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        return null;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        if (isNoCol()) {
            //TriggersMod.logger.info("meow");
            this.setBlockBounds(0, 0, 0, 1, 1, 1);
            //return AxisAlignedBB.getAABBPool().getAABB((double)par2, (double)par3, (double)par4, (double)par2, (double)par3, (double)par4);
        } else {
            this.setBlockBounds(0, 0, 0, 0, 0, 0);
        }
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
        //AxisAlignedBB axisalignedbb = this.getCollisionBoundingBox(worldIn, pos, state);
        // TileSemiEthereal te = getTileEntity(worldIn, pos);
        // toggleTexture(worldIn, pos, state);
        /*
        if (te != null && te.is(collidingEntity)) {
            AxisAlignedBB aabb = AxisAlignedBB.fromBounds(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
            if (aabb != null && aabb.intersectsWith(mask)) list.add(aabb);
            // TriggersMod.logger.info("Adding collision box");
            //list.add(mask);
            //toggleTexture(worldIn, pos, state, true);
        }
        */
        //toggleTexture(worldIn, pos, state, false);
    }


    public TileSemiEthereal getTileEntity(World world, BlockPos pos) {
        //  TriggersMod.logger.info("Getting tile entity");
        return (TileSemiEthereal) world.getTileEntity(pos);
    }

    //@SideOnly(Side.CLIENT)
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(VISIBLE, (meta & 8) != 0);
    }

    //@SideOnly(Side.CLIENT)
    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(VISIBLE) ? 8 : 0);
    }

    //@SideOnly(Side.CLIENT)
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, VISIBLE);
    }

    /**
     * Checks if the player is wearing an active ItemEyeTransient
     * @param player player
     * @return true/false
     */
    public boolean is(EntityPlayer player) {
        //if (what == Property.SOLID) return true;

        ItemStack helmet = player.inventory.armorItemInSlot(3);

        if (helmet == null) return false;

        Item item = helmet.getItem();

        if (item instanceof ItemEyeTransient) {
            return ((ItemEyeTransient) item).checkBlock(helmet);
        }

        return false;
    }

    /**
     * is(), but without caring if the ItemEyeTransient is active or not
     * @param player player
     * @return true/false
     */
    public boolean isNoCol(EntityPlayer player) {
        //if (what == Property.SOLID) return true;

        ItemStack helmet = player.inventory.armorItemInSlot(3);

        if (helmet == null) return false;

        Item item = helmet.getItem();

        if (item instanceof ItemEyeTransient) {
            return true;
        }

        return false;
    }

    public boolean is(Entity e) {
        return (e instanceof EntityPlayer) && is((EntityPlayer) e);
    }

    public boolean is() {
        EntityPlayer player = TriggersMod.ClientProxy.getThePlayer();
        return player != null && is(player);
    }

    public boolean isNoCol() {
        EntityPlayer player = TriggersMod.ClientProxy.getThePlayer();
        return player != null && isNoCol(player);
    }

}
