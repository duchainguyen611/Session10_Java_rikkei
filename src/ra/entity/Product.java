package ra.entity;

import ra.IShop;
import ra.run.ShopManagement;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Product implements IShop {

    private String productId;
    private String productName;
    private float price;
    private String title;
    private int catalogId;
    private int status;

    public Product() {
    }

    public Product(String productId, String productName, float price, String title, int catalogId, int status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.title = title;
        this.catalogId = catalogId;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số sản phẩm cần nhập thông tin:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.printf("Sản phẩm thứ %d:\n", (ShopManagement.listProducts.size() + 1));
            this.productId = inputProductId(scanner, ShopManagement.listProducts);
            this.productName = inputProductName(scanner, ShopManagement.listProducts);
            this.price = inputPrice(scanner);
            this.title = inputTitle(scanner);
            this.catalogId = inputCatalogId(scanner, ShopManagement.listCategories);
            this.status = inputStatus(scanner);
            Product product = new Product(this.productId, this.productName, this.price, this.title, this.catalogId, this.status);
            ShopManagement.listProducts.add(product);
        }
    }

    @Override
    public void displayData() {
        ShopManagement.listProducts.forEach(System.out::println);
    }

    public String toString() {
        String s = switch (this.status) {
            case 0 -> "Hết hàng";
            case 1 -> "Đang bán";
            case 2 -> "Không bán";
            default -> "";
        };
        return "Mã sản phẩm: " + this.productId + " - Tên sản phẩm: " + this.productName + " - Giá sản phẩm: " + this.price +
                "\nTiêu đề: " + this.title + " - Mã danh mục: " + this.catalogId + " - Trạng thái: " + s + "\n";
    }

    public String inputProductId(Scanner scanner, List<Product> listProduct) {
        do {
            System.out.print("Nhập mã sản phẩm:");
            this.productId = scanner.nextLine();
            if (this.productId.length() == 5) {
                if (this.productId.charAt(0) == 'P') {
                    boolean isDuplication = false;
                    for (int i = 0; i < listProduct.size(); i++) {
                        if (this.productId.equals(listProduct.get(i).getProductId())) {
                            isDuplication = true;
                            break;
                        }
                    }
                    if (isDuplication) {
                        System.out.println("Mã sản phẩm bị trùng| Mời nhập lại.");
                    } else {
                        return this.productId;
                    }
                }
            }
        } while (true);
    }

    public String inputProductName(Scanner scanner, List<Product> listProduct) {
        do {
            System.out.print("Nhập tên sản phẩm:");
            this.productName = scanner.nextLine();
            boolean isDuplication = false;
            for (int i = 0; i < listProduct.size(); i++) {
                if (this.productId.equals(listProduct.get(i).getProductName())) {
                    isDuplication = true;
                    break;
                }
            }
            if (isDuplication) {
                System.out.println("Tên sản phẩm bị trùng! Mời nhập lại.\n");
            } else {
                return this.productName;
            }
        } while (true);
    }

    public float inputPrice(Scanner scanner) {
        do {
            System.out.print("Nhập giá sản phẩm:");
            this.price = Float.parseFloat(scanner.nextLine());
            if (this.price > 0) {
                return this.price;
            } else {
                System.out.println("Giá sản phẩm phải lớn hơn 0");
            }
        } while (true);
    }

    public String inputTitle(Scanner scanner) {
        System.out.print("Nhập tiêu đề sản phẩm:");
        this.title = scanner.nextLine();
        return this.title;
    }

    public int inputCatalogId(Scanner scanner, List<Categories> listCategories) {
        Categories categories = new Categories();
        System.out.println("Thông tin các danh mục:");
        categories.displayData();
        do {
            System.out.print("Nhập mã danh mục mà sản phẩm thuộc về:");
            this.catalogId = Integer.parseInt(scanner.nextLine());
            boolean isExit = false;
            for (int i = 0; i < listCategories.size(); i++) {
                if (this.catalogId == listCategories.get(i).getCatalogId()) {
                    isExit = true;
                    break;
                }
            }
            if (isExit) {
                return this.catalogId;
            } else {
                System.out.println("Mã danh mục không tồn tại! Mời nhập lại!");
            }
        } while (true);
    }

    public int inputStatus(Scanner scanner) {
        do {
            System.out.print("Nhập trạng thái sản phẩm:");
            this.status = Integer.parseInt(scanner.nextLine());
            if (this.status == 0 || this.status == 1 || this.status == 2) {
                return this.status;
            } else {
                System.out.println("Trạng thái sản phẩm chỉ có giá trị 0, 1 hoặc 2! Mời nhập lại!");
            }
        } while (true);
    }

    public void updateProduct(Scanner scanner, List<Product> listProduct) {
        int updateIndex = checkIdProduct(listProduct, scanner);
        if (updateIndex >= 0) {
            listProduct.get(updateIndex).setPrice(inputPrice(scanner));
            System.out.print("Cập nhật thành công!");
        }
    }

    public void deleteProduct(Scanner scanner, List<Product> listProduct) {
        int deleteIndex = checkIdProduct(listProduct, scanner);
        if (deleteIndex >= 0) {
            listProduct.remove(deleteIndex);
            System.out.print("Xóa thành công!");
        }
    }

    public int checkIdProduct(List<Product> listProduct, Scanner scanner) {
        boolean isExit = false;
        do {
            System.out.print("Nhập mã sản phẩm:");
            String id = scanner.nextLine();
            for (int i = 0; i < listProduct.size(); i++) {
                if (id.equals(listProduct.get(i).getProductId())) {
                    return i;
                }
            }
            if (!isExit) {
                System.out.println("Mã sản phẩm không tồn tại! Mời nhập lại!");
            }
        } while (true);
    }

    public void sortProductByPrice(List<Product> listProduct) {
        listProduct.sort((o1, o2) -> (int) (o1.price - o2.price));
        System.out.println("Sắp xếp thành công! Ấn 2 kiểm tra kết quả!");
    }

    public void sortProductByName(List<Product> listProduct) {
        listProduct.sort(Comparator.comparing(o -> o.productName));
        System.out.println("Sắp xếp thành công! Ấn 2 kiểm tra kết quả!");
    }

    public void lookForProductByName(List<Product> listProduct, Scanner scanner) {
        System.out.print("Nhập tên sản phẩm tìm kiếm:");
        String name = scanner.nextLine();
        boolean isCheck = false;
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getProductName().contains(name)) {
                isCheck = true;
                System.out.println(listProduct.get(i).toString());
                break;
            }
        }
        if (!isCheck) {
            System.out.print("Tên sản phẩm không tồn tại");
        }
    }

    public void statisticsByCategories(List<Categories> listCategories, List<Product> listProduct) {
        System.out.println("Thống kê số lượng sản phẩm theo danh mục sản phẩm:");
        for (int i = 0; i < listCategories.size(); i++) {
            int quantity = countProduct(listCategories.get(i).getCatalogId(), listProduct);
            String name = listCategories.get(i).getCatalogName();
            System.out.printf("Tên danh mục %s: %d sản phẩm\n", name, quantity);
        }
    }

    public int countProduct(int idCatalog, List<Product> listProduct) {
        int count = 0;
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getCatalogId() == idCatalog) {
                count++;
            }
        }
        return count;
    }

}
