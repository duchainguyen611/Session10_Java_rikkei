package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopManagement {

    public static List<Categories> listCategories = new ArrayList<>();

    public static List<Product> listProducts = new ArrayList<>();

    public static void main(String[] args) {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n*************************SHOP MANAGEMENT***************\n" +
                    "1. Quản lý danh mục sản phẩm\n" +
                    "2. Quản lý sản phẩm\n" +
                    "3. Thoát");
            System.out.print("Nhập lựa chọn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    catalogMenu(scanner);
                    break;
                case 2:
                    productMenu(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Mời nhập từ 1 đến 3!");
                    break;
            }
        } while (true);
    }

    public static void catalogMenu(Scanner scanner) {
        Categories categories = new Categories();
        boolean isExit = true;
        do {
            System.out.println("\n***************** CATALOG MANAGEMENT**************\n" +
                    "1. Thêm mới danh mục\n" +
                    "2. Hiển thị thông tin các danh mục\n" +
                    "3. Cập nhật tên danh mục theo mã danh mục\n" +
                    "4. Xóa danh mục theo mã danh mục (Danh mục chưa chứa sản phẩm)\n" +
                    "5. Thoát (Quay lại Shop Management)");
            System.out.print("Nhập lựa chọn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    categories.inputData();
                    break;
                case 2:
                    categories.displayData();
                    break;
                case 3:
                    categories.updateCatalog(scanner, listCategories);
                    break;
                case 4:
                    categories.deleteCatalog(scanner, listCategories, listProducts);
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.out.println("Mời nhập từ 1 đến 5!");
                    break;
            }
        } while (isExit);
    }

    public static void productMenu(Scanner scanner) {
        Product product = new Product();
        boolean isExit = true;
        do {
            System.out.println("\n***************** PRODUCT MANAGEMENT**************\n" +
                    "1. Thêm mới sản phẩm (Khi thêm cho phép chọn danh mục sản phẩm \n" +
                    "mà sản phẩm thuộc về)\n" +
                    "2. Hiển thị thông tin sản phẩm\n" +
                    "3. Cập nhật giá sản phẩm theo mã sản phẩm\n" +
                    "4. Xóa sản phẩm theo mã sản phẩm\n" +
                    "5. Sắp xếp sản phẩm theo giá sản phẩm tăng dần\n" +
                    "6. Sắp xếp sản phẩm theo tên tăng dần\n" +
                    "7. Thống kê số lượng sản phẩm theo danh mục sản phẩm\n" +
                    "8. Tìm kiếm sản phẩm theo tên sản phẩm\n" +
                    "9. Thoát (Quay lại Shop Management)");
            System.out.print("Nhập lựa chọn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    product.inputData();
                    break;
                case 2:
                    product.displayData();
                    break;
                case 3:
                    product.updateProduct(scanner, listProducts);
                    break;
                case 4:
                    product.deleteProduct(scanner, listProducts);
                    break;
                case 5:
                    product.sortProductByPrice(listProducts);
                    break;
                case 6:
                    product.sortProductByName(listProducts);
                    break;
                case 7:
                    product.statisticsByCategories(listCategories, listProducts);
                    break;
                case 8:
                    product.lookForProductByName(listProducts, scanner);
                    break;
                case 9:
                    isExit = false;
                    break;
                default:
                    System.out.println("Mời nhập từ 1 đến 9!");
                    break;
            }
        } while (isExit);
    }
}
