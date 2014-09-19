package org.testinfected.petstore.db;

import org.testinfected.petstore.billing.PaymentMethod;
import org.testinfected.petstore.db.records.ItemRecord;
import org.testinfected.petstore.db.records.LineItemRecord;
import org.testinfected.petstore.db.records.OrderRecord;
import org.testinfected.petstore.db.records.PaymentRecord;
import org.testinfected.petstore.db.records.ProductRecord;
import org.testinfected.petstore.db.support.Table;
import org.testinfected.petstore.db.support.TableSchema;
import org.testinfected.petstore.order.LineItem;
import org.testinfected.petstore.order.Order;
import org.testinfected.petstore.product.Item;
import org.testinfected.petstore.product.Product;

public final class Schema {

    private Schema() {
    }

    public static Table<Product> products() {
        TableSchema schema = new TableSchema("products");
        return new Table<Product>(schema, new ProductRecord(
                schema.LONG("id"),
                schema.STRING("number"),
                schema.STRING("name"),
                schema.STRING("description"),
                schema.STRING("photo_file_name")));
    }

    public static Table<Item> items(Table<Product> products) {
        TableSchema schema = new TableSchema("items");
        return new Table<Item>(schema, new ItemRecord(
                schema.LONG("id"),
                schema.STRING("number"),
                schema.LONG("product_id"),
                schema.BIG_DECIMAL("price"),
                schema.STRING("description"),
                products));
    }

    public static Table<LineItem> lineItems() {
        TableSchema schema = new TableSchema("line_items");
        return new Table<LineItem>(schema, new LineItemRecord(
                schema.LONG("id"),
                schema.STRING("item_number"),
                schema.STRING("item_description"),
                schema.BIG_DECIMAL("item_unit_price"),
                schema.INT("quantity"),
                schema.BIG_DECIMAL("total_price"),
                schema.LONG("order_id"),
                schema.INT("order_line")));
    }

    public static Table<PaymentMethod> payments() {
        TableSchema schema = new TableSchema("payments");
        return new Table<PaymentMethod>(schema, new PaymentRecord(
                schema.LONG("id"),
                schema.STRING("payment_type"),
                schema.STRING("card_type"),
                schema.STRING("card_number"),
                schema.STRING("card_expiry_date"),
                schema.STRING("billing_first_name"),
                schema.STRING("billing_last_name"),
                schema.STRING("billing_email"),
                schema.STRING("billing_country"),
                schema.STRING("billing_street"),
                schema.STRING("billing_zip_code"))
        );
    }

    public static Table<Order> orders(Table<PaymentMethod> payments) {
        TableSchema schema = new TableSchema("orders");
        return new Table<Order>(schema, new OrderRecord(
                schema.LONG("id"),
                schema.STRING("number"),
                schema.LONG("payment_id"),
                payments));
    }
}