export class ConfigConstants {
    public static readonly DATE_FORMAT = "yyyy-mm-dd";
    public static readonly DATE_FORMAT_AG_DATEPIPE_SUPPORT = "yyyy-MMM-dd";
    public static readonly STRING_KEY = "String";
    public static readonly NUMBER_KEY = "String";
    public static readonly DATE_KEY = "Date";
    public static readonly DECIMAL_KEY = "BigDecimal";
    public static readonly INTEGER_KEY = "Integer";

    //Routing - start
    public static readonly ROUTER_MAP_KEY = "routerMap";
    public static readonly ROUTER_MAP_TRUE = "true";
    public static readonly ROUTER_MAP_FALSE = "false";
    public static readonly ROUTER_MAP_OFFER_LIST_PAGE_CONFIGURE_OFFER_KEY = "expandOfferList";
    public static readonly ROUTER_MAP_OFFER_ITEM_LIST_PAGE_CONFIGURE_OFFER_KEY = "expandOfferItemList";
    public static readonly ROUTER_MAP_OFFER_CART_PAGE_CONFIGURE_OFFER_KEY = "expandCartList";
    public static readonly ROUTER_MAP_OFFER_PRODUCT_SEARCH_PAGE_CONFIGURE_OFFER_KEY = "expandProductSearch";
    //Routing - end

    //Errors start
    public static readonly DBN_PHONE_NUM_CHANGE_STATIC_ATRBT_NOT_CONFIG = "Attributes are not configured properly";
    public static readonly PRODUCT_IS_NOT_SET = "Product is not set.";
    public static readonly PRODUCT_IS_NOT_SELECTED = "Please select a product inorder to apply";
    public static readonly INSTALLMENT_IS_NOT_SELECTED = "Please select an installment inorder to apply";
    public static readonly STOCK_NULL = "Stocks not available";
    public static readonly SELECTED_INSTALLMENT_IS_ZERO = "Selected installment has zero value for installment";

    //RegEx
    public static readonly DBN_PHONE_REG = "^[0-9]{9}$";
    public static readonly PHONE_NO_START_WITH = "^([\\s]*|[0-9]{4})$";
    public static readonly PHONE_NO_INCLUDE = "^([\\s]*|[0-9]{1,7})$";
    public static readonly GSM_PHONE_REG = "^[0-9]{9}$";
    public static readonly DATE_FORMAT_REG = '^\d{4}-\d{2}-\d{2}$';

    public static readonly DATE_FORMAT_REG_TXT = '\d{4}-\d{2}-\d{2}';

}