import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;


public class Generator {
	
	private Field field=new Field4();
	private int N=15;
	private int h=4;
	private int [] message=new int [N];
	//private int [] polinom={1,field.getValue(3),1,field.getValue(1),field.getValue(3)};
	private int [] polinom={field.getValue(3),field.getValue(1),1,field.getValue(3)};
	public String generate(int [] information)  throws Exception{
		if(information.length!=N-h)
			throw new Exception("Invalid informational part");
		//copy info to message
		for(int i=0;i<N-h;i++)
			message[i]=information[i];
		int degree=0;
		int tmp1[]=Arrays.copyOf(message, N);
		int tmp2[]=new int[N];
		int tmp3[]=new int[N];
		do{
			//clear
			int alpha=tmp1[degree];
			for(int i=0;i<polinom.length;i++){
				tmp2[degree+i]=field.multiplication(alpha,polinom[i]);
			}
			for(int i=0;i<N;i++){
				tmp3[i]=field.addition(tmp1[i],(tmp2[i]));
			}
			Arrays.fill(tmp2, 0);
			tmp1=Arrays.copyOf(tmp3, N);
			Arrays.fill(tmp3, 0);
			degree=searchNotZero(tmp1);
			System.out.println("Degree "+degree);
		}while(degree<N-h);
		outputArray(tmp1);
		if(searchNotZero(tmp1)!=N-h)
			throw new Exception("Invalid control bits!");
		for(int i=N-h;i<N;i++){
			message[i]=tmp1[i];
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<N;i++){
			String s=Integer.toString(message[i], 2);
			String str=" ";
			if(s.length()<3){
				int d=3-s.length();
				for(int j=0;j<d;j++)
					str+="0";
			}
			sb.append(str);
			sb.append(s);
		}
		return sb.toString();
	}
	public int searchNotZero(int [] array){
		int result=-1;
		int i=0;
		do{} while(i<array.length && array[i++]==0 );
		if(i<N && array[i-1]!=0)
			result=i-1;
		return result;
	}
	public static void main(String...args){
		Generator g=new Generator();
		try {
			System.out.println(g.generate(new int []{1,2,3,4,5,6,7,8,9,10,11}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Message m=new Message(g.getMessage(),new int []{0,11,4,0,0,0,0,0,0,0,0,0,0,0,0});
		FileOutputStream fout=null;
		try {
			fout = new FileOutputStream("result.txt");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ObjectOutputStream oos=null;
		try {
			oos = new ObjectOutputStream(fout);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			oos.writeObject(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int[] getMessage() {
		return message;
	}
	public void setMessage(int[] message) {
		this.message = message;
	}
	public void outputArray(int [] arr){
		for(int i=0;i<arr.length;i++)
			System.out.print(" "+arr[i]);
		System.out.println();
	}
	
	
}
