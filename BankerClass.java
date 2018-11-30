package ���м��㷨;

import java.util.Scanner;

public class BankerClass {

	// ϵͳ�ܽ�����
	int pro_num = TestBankerClass.pro;
	// ϵͳ�ٽ���Դ����
	int os_num = TestBankerClass.os;

	// ��������õ�3����Դ�����ֱ�Ϊ10,8,7
	int[] Available = new int[os_num];

	// ����Pi����Pj��Դ�������ΪMax[i][j]
	int[][] Max = new int[pro_num][os_num];

	// ����Pi�Ѿ�����Pj��ԴAllocation[i][j]
	int[][] Allocation = new int[pro_num][os_num];

	// ����Pi����Ҫ����Pj��ԴΪNeed[i][j]
	int[][] Need = new int[pro_num][os_num];

	// ���ֽ���Pi����Pj��Դ��ΪRequest[i][j]
	int[][] Request = new int[pro_num][os_num];

	// ������������¼������Դ
	int[] Work = new int[os_num];

	// ��¼���̱��
	int num = 0;

	Scanner in = new Scanner(System.in);

	// Max={{6,3,2},{5,6,1},{2,3,2}};

	public BankerClass() {// ���ø���ʼϵͳ���������ж��Ƿ��ڰ�ȫ״̬��
		// �û���ʾ
		System.out.print("��ϵͳӵ��" + os_num + "���ٽ���Դ�������ֱ�Ϊ");
		Available = Ginput(os_num);
		for (int j = 0; j < os_num; j++) {
			System.out.print((char) ('A' + j) + ":" + Available[j] + ",");
		}
		System.out.println();
		setMax();
		setAllocation();
		printSystemVariable();// ���
		if (!SecurityAlgorithm()) {// ��ȫ���㷨
			System.exit(0);
		}
	}

	public void setMax() {// ����Max����
		System.out.println("�����ý��̶Ը���Դ�������������");
		for (int i = 0; i < pro_num; i++) {
			System.out.println("    ���������P" + i + "�Ը���Դ�������Դ��������");
			Max[i] = Ginput(os_num);
			for (int j = 0; j < os_num; j++) {
				if (Max[i][j] > Available[j]) {
					System.out.println("��Դ���㣬���������룡");
					Max[i] = Ginput(os_num);
					j = 0;
				}
			}
		}
	}

	public void setAllocation() {// �����ѷ������Allocation
		System.out.println("�������Ѹ������̷������Դ����");
		for (int i = 0; i < pro_num; i++) {
			System.out.println("    ���������P" + i + "���ѷ�����Դ����");
			Allocation[i] = Ginput(os_num);
			for (int j = 0; j < os_num; j++) {
				if (Allocation[i][j] > Max[i][j]) {
					System.out.println("����Լ����Դ�������������룡");
					Allocation[i] = Ginput(os_num);
					j = 0;
				}
			}
		}
		// ���µ�ǰ��Դ�������������
		// Available=Available-Allocation
		// Need=Max-Allocation

		for (int j = 0; j < os_num; j++) {// ����Available����
			for (int i = 0; i < pro_num; i++) {
				Available[j] = Available[j] - Allocation[i][j];
			}
		}
		for (int i = 0; i < pro_num; i++) {// ����Need����
			for (int j = 0; j < os_num; j++) {
				Need[i][j] = Max[i][j] - Allocation[i][j];
			}
		}
	}

	public void printSystemVariable() {// �������
		System.out.println("��ʱ��Դ���������£�");// ��ʾ������
		// ��ͷ
		System.out.println("����\t\t" + "Max\t\t" + "Allocation\t\t" + "Need\t" + "Available");

		for (int i = 0; i < pro_num; i++) {
			System.out.print("P" + i + "\t\t");
			for (int j = 0; j < os_num; j++) {
				System.out.print(Max[i][j] + "  ");
			}
			System.out.print("\t\t|  ");
			for (int j = 0; j < os_num; j++) {
				System.out.print(Allocation[i][j] + "  ");
			}
			System.out.print("\t\t\t|  ");
			for (int j = 0; j < os_num; j++) {
				System.out.print(Need[i][j] + "  ");
			}
			System.out.print("\t\t|  ");

			if (i == 0) {// ֻ�ڵ�һ�������ǰ��Դ������
				for (int j = 0; j < os_num; j++) {
					System.out.print(Available[j] + "  ");
				}
			}
			System.out.println();
		}
	}

	public void setRequest() {// ����������Դ��Request
		System.out.println("������������Դ�Ľ��̱�ţ�");
		while (true) {
			num = Ginput(1)[0];// ����ȫ�ֱ������̱��num
			if (num >= pro_num)
				System.out.println("�޴˽��̣�");
			else
				break;
		}
		System.out.println("������P" + num + "�������Դ��������");
		Request[num]=Ginput(os_num);
		System.out.print("������P" + num + "�Ը���Դ��������Ϊ��(");
		for (int i = 0; i < os_num; i++) {
			System.out.print(Request[num][i] + ",");
		}
		System.out.println(").");
		// �������м��㷨
		BankerAlgorithm();
	}

	public void BankerAlgorithm() {// ���м��㷨
		// ���建�����飬���浱��ȫ�㷨�ж�ʧ�ܵĽ��
		int[][] cache = new int[3][os_num];
		// �м��жϱ���T
		boolean[] T = { true, true };// �ж����м��㷨�����������Ƿ����
		// 1.�ж�Request�Ƿ�С��Need
		for (int j = 0; j < os_num; j++) {
			if (Request[num][j] > Need[num][j]) {
				T[0] = false;
				j = os_num;
			}
		}
		// 2.�ж�Request�Ƿ�С��Allocation
		for (int j = 0; j < os_num; j++) {
			if (Request[num][j] <= Available[j])
				;
			else {
				T[1] = false;
				j = os_num;
			}
		}
		if (T[0]) {
			if (T[1]) {
				// �Է�����Դ
				for (int j = 0; j < os_num; j++) {
					cache[0][j] = Available[j];
					cache[1][j] = Allocation[num][j];
					cache[2][j] = Need[num][j];

					Available[j] -= Request[num][j];
					Allocation[num][j] += Request[num][j];
					Need[num][j] -= Request[num][j];
				}
			} else {// ʣ����Դ����
				System.out.println("��ǰû���㹻����Դ�ɷ��䣬����P" + num + "��ȴ���");
				System.out.println("......");
			}
		} else {// ���󳬳��������������max
			System.out.println("����P" + num + "�����Ѿ��������������Need.");
			System.out.println("......");
		}

		if (T[0] && T[1]) {
			printSystemVariable();
			System.out.println("���ڽ��밲ȫ�㷨��");
			if (!SecurityAlgorithm()) {// �����ȫ�ж�ʧ�ܣ�������һ��
				for (int j = 0; j < os_num; j++) {
					Available[j] = cache[0][j];
					Allocation[num][j] = cache[1][j];
					Need[num][j] = cache[2][j];
				}
			}
		} else// ����ʧ�ܣ���������
			setRequest();
	}

	public boolean SecurityAlgorithm() {// ��ȫ�㷨
		boolean[] Finish = new boolean[pro_num];// �����㹻��Դ��������� Pi
												// ʱ����finish[i]=true

		int count = 0;// ��ɽ�����
		int[] safe = new int[pro_num];// ��ȫ����

		for (int i = 0; i < pro_num; i++) {// ��ʼ��Finish����
			Finish[i] = false;
		}
		for (int j = 0; j < os_num; j++) {// ��ʼ����������
			Work[j] = Available[j];
		}
		System.out.println("����\t\t" + "Work\t" + "Allocation\t\t" + "Need\t" + "Available");
		for (int i = 0; i < pro_num; i++) {
			boolean T = true;// �м����
			for (int j = 0; j < os_num; j++) {// �ж���Դ�����Ƿ�����ɽ���Pi
				if (Need[i][j] > Work[j]) {
					T = false;
					j = os_num;
				}
			}
			if (Finish[i] == false && T) {
				System.out.print("P" + i + "\t\t");
				for (int j = 0; j < os_num; j++) {
					System.out.print(Work[j] + "  ");
				}
				System.out.print("\t\t|  ");
				for (int j = 0; j < os_num; j++) {
					System.out.print(Allocation[i][j] + "  ");
				}
				System.out.print("\t\t\t|  ");
				for (int j = 0; j < os_num; j++) {
					System.out.print(Need[i][j] + "  ");
				}
				System.out.print("\t\t|  ");

				for (int j = 0; j < os_num; j++) {
					Work[j] += Allocation[i][j];
				}
				for (int j = 0; j < os_num; j++) {
					System.out.print(Work[j] + "  ");
				}
				System.out.println();
				Finish[i] = true;// ��ǰ����������ʱ
				safe[count] = i;// ���浱ǰ�������
				if (i != 0)// ������work[]�����ж�
					i = -1;
				count++;// �����������1
			}
		}
		if (count < pro_num) {// �ж���ɽ������Ƿ�С���ܽ�����
			System.out.println("....... .......");
			System.out.println("��ǰϵͳ�����ڰ�ȫ���С�����Ԥ����ĸ���Դ��");
			return false;// ��ȫ�ж�ʧ�ܣ���������
		} else {
			System.out.print("��ʱ���ٴ���һ����ȫ���У�");
			for (int i = 0; i < pro_num; i++) {// �����ȫ����
				System.out.print("P" + safe[i] + "->");
			}
			System.out.println("�ʵ�ǰ�ɷ��䣡");
			return true;
		}
	}

	// �淶����������ݣ�ʶ������1,2,3��ֱ���ַ���������Υ������P1
	// num ��Ҫʶ�������
	public int[] Ginput(int num) {
		int[] putnum = new int[num];// ���յ��������
		char[] s = in.nextLine().toCharArray();// ���û�������ַ���ת�����ַ�����
		int[] after = new int[s.length];// ���ַ��������ʱ����
		int[] cache = { 0, 0 };// ��λ��������������λ����¼�Ѿ���ȡ��������
		for (int i = 0; i < s.length; i++) {
			if (cache[1] >= num) {// ��ȡ�����Ⱥ󣬺���ĺ���
				break;
			}
			if ((int) s[i] >= 48 && (int) s[i] <= 57) {// ASCII��ĸ�λ����48-57���ж��ǲ�������
				cache[0]++;
				after[i] = (int) s[i] - 48;// ��ʱ������һλ��
				if (i == s.length - 1) { // ���������һλ��Ϊ���֡�ֱ�ӽ��кϳɲ���
					for (int t = cache[0]; t > 0; t--) {
						putnum[cache[1]] += after[i + 1 - t] * (int) Math.pow(10, (t - 1));
					}
				}
			} else {
				for (int t = cache[0]; t > 0; t--) {// �Ѿ��������������֣�����λ���ϲ�
					putnum[cache[1]] += after[i - t] * (int) Math.pow(10, (t - 1));
				}
				if (cache[0] != 0)
					cache[1]++;// ��һ������������ʱ�Ѷ�ȡ��+1
				cache[0] = 0; // ���ȹ���
			}
		}
		return putnum;// ������ȡ����Ч����
	}

}