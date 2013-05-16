package game.entity.item;
public class Item
{
    public static Item stick = new Item("Stick",Rarity.common, 1, 1);
    public static Item bucket = new Item("Bucket", Rarity.common, 3, 2);
    public static Item bucketSpit = new Item("Bucket O' Spit", Rarity.spitbucket, 0, 3);
    public static Item bucketWater = new Item("Bucket of Water", Rarity.common, 3, 4);
    
    @SuppressWarnings("unused")
	private static String name;
    @SuppressWarnings("unused")
	private static Rarity rare;
    @SuppressWarnings("unused")
	private static int value;
    @SuppressWarnings("unused")
	private static int code;
    Item(String Name, Rarity Rare, int Value, int itemCode)
    {
        name = Name;
        rare = Rare;
        value = Value;
        
    }
    private enum Rarity{spitbucket, useless, common, uncommon, rare, unknown}
}
