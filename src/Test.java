import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {

        boolean bool = true;
        while (bool) {
            System.out.print("请输入用户名:");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.print("请输入密码:");
            String password = sc.next();
            //File file=new File("C:\\Users\\lenovo\\IdeaProjects\\ConsoleShop\\src\\Users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/Users.xlsx");
            InputStream inp = Class.forName("Test").getResourceAsStream("/product.xlsx");
            ReadExcel re = new ReadExcel();
            User users[] = re.readExcel(in);
            boolean flag = false;
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    bool=false;
                    ReadProductExcel rpe = new ReadProductExcel();
                    Product products[] = rpe.getAllProduct(inp);
                    for(Product product:products){
                        System.out.print(product.getpId());
                        System.out.print("\t" + product.getpName());
                        System.out.print("\t" + product.getPrice());
                        System.out.println("\t" + product.getpDescri());
                    }
                    int count=0;
                    Product productes[]=new Product[5];
                    System.out.print("请输入商品ID，把该商品加入购物车：");
                    String pId=sc.next();
                    ReadProductExcel rpe1=new ReadProductExcel();
                    inp=null;
                    inp=Class.forName("Test").getResourceAsStream("/product.xlsx");
                    Product product=rpe1.getProductById(pId,inp);
                    if(product!=null){
                        productes[count++]=product;
                        System.out.println("加入成功！");
                    }
                    flag = true;
                    break;
                } else if (flag) {
                    System.out.println("登录失败");
                }
            }
        }
    }
}
