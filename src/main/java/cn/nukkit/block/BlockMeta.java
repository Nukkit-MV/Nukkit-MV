package cn.nukkit.block;

public abstract class BlockMeta extends Block {

    private int meta;

    protected BlockMeta(int meta) {
        this.meta = meta;
    }

    @Override
    public int getFullId() {
        return (getId() << DATA_BITS) + meta;
    }

    @Override
    public final int getDamage() {
        return this.meta;
    }

    @Override
    public void setDamage(int meta) {
        this.meta = meta;
    }
}