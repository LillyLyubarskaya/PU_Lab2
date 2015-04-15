
public class Field4 implements Field{
	private int N=15;
	private int [] alpha={0b0001,0b0010,0b0100,0b1000,0b0011,0b0110,0b1100,0b1011,0b0101,0b1010,0b111,0b1110,0b1111,0b1101,0b1001};
	public int getValue(int index){
		return alpha[index];
	}
	public int getIndex(int value){
		int result=-1;
		for(int i=0;i<N;i++)
			if(alpha[i]==value)
				result=i;
		return result;
	}
	public int addition(int x1,int x2){
		return x1^x2;
	}
	public int multiplication(int x1,int x2){
		if(x1!=0 && x2!=0){
			int i1=getIndex(x1);
			int i2=getIndex(x2);
			return alpha[(i2+i1)%(N)];
		}
		return 0;
	}
	public int degree(int x1,int x2){
		int result=x1;
		if(x1!=0 && x2!=0){
			for(int i=2;i<=x2;i++){
				result=multiplication(result, x1);
			}
		}
		return result;
	}
	public static void main(String...args){
		Field4 field=new Field4();
		System.out.println(Integer.toString(field.multiplication(0b0001, 0b0001), 2));
		System.out.println(Integer.toString(field.addition(0b100, 0b10), 2));
	}

}
