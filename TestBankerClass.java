package ���м��㷨;

import java.util.Scanner;

public class TestBankerClass {

	public static int pro = 0;
	public static int os = 0;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner main_in = new Scanner(System.in);
		// ��ʼ��ϵͳ��Դ
		System.out.print("����ϵͳ��Դ��������");
		os = main_in.nextInt();
		System.out.print("����ϵͳ��������");
		pro = main_in.nextInt();
		BankerClass T = new BankerClass();
		// �����û�ѡ���˳�ϵͳʱ�˳�
		while (true) {
			T.setRequest();
			System.out.println("���Ƿ�Ҫ��������y/n ?");
			boolean flag=true;
			while ( flag) {
				String s=main_in.next();//��ʱ����
				if (s.equals("n")) {
					System.out.println("��лʹ�ã�");
					System.exit(0);// �˳�
				}else 
					if(s.equals("y"))
						flag=false;
				else{
					System.out.println("���벻�淶��");
				}
			}
		}
	}
}