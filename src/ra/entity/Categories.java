package ra.entity;

import ra.IShop;
import ra.run.ShopManagement;

import java.util.List;
import java.util.Scanner;

public class Categories implements IShop {

    private int catalogId;
    private String catalogName;
    private Boolean status;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, Boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.status = status;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số danh mục cần nhập thông tin:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.printf("Danh mục thứ %d:\n", ShopManagement.listCategories.size() + 1);
            this.catalogId = inputCatalogId(ShopManagement.listCategories);
            this.catalogName = inputCatalogName(ShopManagement.listCategories, scanner);
            this.status = inputCatalogStatus(scanner);
            Categories categorie = new Categories(this.catalogId, this.catalogName, this.status);
            ShopManagement.listCategories.add(categorie);
        }
    }

    @Override
    public void displayData() {
        ShopManagement.listCategories.forEach(System.out::println);
    }

    public String toString() {
        String s;
        if (this.status) {
            s = "Hoạt động";
        } else {
            s = "Không hoạt động";
        }
        return "Mã danh mục: " + this.catalogId + " - Tên danh mục: " + this.catalogName + " - Trạng thái danh mục: " + s + " \n";
    }

    public int inputCatalogId(List<Categories> listCategories) {
        if (listCategories.isEmpty()) {
            return 1;
        } else {
            int max = listCategories.get(0).getCatalogId();
            for (int i = 1; i < listCategories.size(); i++) {
                if (max < listCategories.get(i).getCatalogId()) {
                    max = listCategories.get(i).getCatalogId();
                }
            }
            return max + 1;
        }
    }

    public String inputCatalogName(List<Categories> listCategories, Scanner scanner) {
        do {
            System.out.print("Nhập tên danh mục sản phẩm:");
            this.catalogName = scanner.nextLine();
            boolean isDuplication = false;
            for (int i = 0; i < listCategories.size(); i++) {
                if (this.catalogName.equals(listCategories.get(i).getCatalogName())) {
                    isDuplication = true;
                    break;
                }
            }
            if (isDuplication) {
                System.out.println("Tên danh mục bị trùng! Mời nhập lại.\n");
            } else {
                return this.catalogName;
            }
        } while (true);
    }

    public Boolean inputCatalogStatus(Scanner scanner) {
        do {
            System.out.print("Nhập trạng thái danh mục:");
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")) {
                this.status = Boolean.parseBoolean(status);
                return this.status;
            } else {
                System.out.println("Trạng thái danh mục chỉ nhận giá trị true or false! Mời nhập lại\n");
            }
        } while (true);
    }

    public void updateCatalog(Scanner scanner, List<Categories> listCategories) {
        int updateIndex = checkIdCatalog(listCategories, scanner);
        if (updateIndex >= 0) {
            listCategories.get(updateIndex).setCatalogName(inputCatalogName(listCategories,scanner));
            System.out.print("Cập nhật thành công!");
        }
    }

    public void deleteCatalog(Scanner scanner, List<Categories> listCategories,List<Product> listProduct) {
        int deleteIndex = checkIdCatalog(listCategories, scanner);
        if (deleteIndex >= 0) {
            boolean isCheck = false;
            for (int i = 0; i < listProduct.size(); i++) {
                if (listProduct.get(i).getCatalogId() != listCategories.get(deleteIndex).getCatalogId()){
                    isCheck = true;
                    listCategories.remove(deleteIndex);
                    System.out.print("Xóa thành công!");
                    break;
                }
            }
            if (!isCheck){
                System.out.print("Danh mục đang chứa sản phẩm nên không xóa được !");
            }
        }

    }

    public int checkIdCatalog(List<Categories> listCategories, Scanner scanner) {
        boolean isExit = false;
        do {
            System.out.print("Nhập mã danh mục:");
            int id = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < listCategories.size(); i++) {
                if (id == listCategories.get(i).getCatalogId()) {
                    return i;
                }
            }
            if (!isExit) {
                System.out.println("Mã danh mục không tồn tại! Mời nhập lại!");
            }
        } while (true);
    }
}
