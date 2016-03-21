package variables;

public class Values {
	// name of the tables
	public final static String[] TABLES = { "--SELECT A TABLE", "Clothing", "Contains", "Customer", "CutsOrder",
			"Equipment", "Manufacturer", "PaymentInfo", "Shipper", "Ships", "Shoes", "SportEquipment" };

	// the attributes of the each table
	public static String[] attributesTables(String name) {
		switch (name) {
		case "Clothing":
			return new String[] { "cloth_size", "itemId" };
		case "Contains":
			return new String[] { "orderid", "itemId", "quantity" };
		case "Customer":
			return new String[] { "name", "username", "email", "phone", "address" };
		case "CutsOrder":
			return new String[] { "order_status", "totalprice", "numberoncc" };
		case "Equipment":
			return new String[] { "itemId" };
		case "Manufacturer":
			return new String[] { "billingAddress" };
		case "PaymentInfo":
			return new String[] { "numberOnCC", "nameOnCC", "billingAddress", "expMonth", "expYear", "pid" };
		case "Shipper":
			return new String[] { "company_name", "date_shipped", "shipment_type" };
		case "Ships":
			return new String[] { "orderid", "shipperid" };
		case "Shoes":
			return new String[] { "Shoe_size", "itemid" };
		case "SportEquipment":
			return new String[] { "description", "price", "gender", "item_count", "ageGroup", "sport_category", "mid" };
		}
		return null;
	}

	// attributes of tables, but formatted
	public static String[] formattedAttributesTables(String name) {
		switch (name) {
		case "Clothing":
			return new String[] { "'cloth_size'", "itemId" };
		case "Contains":
			return new String[] { "orderid", "itemId", "quantity" };
		case "Customer":
			return new String[] { "'name'", "'username'", "'email'", "'phone'", "'address'" };
		case "CutsOrder":
			return new String[] { "'order_status'", "'totalprice'", "'numberoncc'" };
		case "Equipment":
			return new String[] { "itemId" };
		case "Manufacturer":
			return new String[] { "'billingAddress'" };
		case "PaymentInfo":
			return new String[] { "'numberOnCC'", "'nameOnCC'", "'billingAddress'", "'expMonth'", "'expYear'", "pid" };
		case "Shipper":
			return new String[] { "'company_name'", "'date_shipped'", "'shipment_type'" };
		case "Ships":
			return new String[] { "orderid", "shipperid" };
		case "Shoes":
			return new String[] { "Shoe_size", "itemid" };
		case "SportEquipment":
			return new String[] { "'description'", "'price'", "'gender'", "item_count", "'ageGroup'",
					"'sport_category'", "mid" };
		}
		return null;
	}

	public final static String[] SHOESIZE = { "10", "7", "M", "X" };

	// status of order
	public final static String[] STATUS = { "--SELECT A STATUS", "'Completed'", "'Processing'", "'Shipped'" };

	public final static String[] ITEM = { "Bauer graphite Hockey Stick of size medium",
			"Wilson aluminium Tennis Racket", "Nike Hockey gloves", "Adidas Soccer pads Messi edition",
			"Under Armour Sweat Band", "Brooks Pure Running Shoes" };

}
