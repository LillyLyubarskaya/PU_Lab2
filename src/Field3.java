
public class Field3 implements Field{
	private int N=7;
	private int [] alpha={0b001,0b010,0b100,0b011,0b110,0b111,0b101};
	@Override
	public int multiplication(int x1, int x2) {
		if(x1!=0 && x2!=0){
			int i1=getIndex(x1);
			int i2=getIndex(x2);
			return alpha[(i2+i1)%(N)];
		}
		return 0;
	}

	@Override
	public int addition(int x1, int x2) {
		return x1^x2;
	}

	@Override
	public int getIndex(int value) {
		int result=-1;
		for(int i=0;i<N;i++)
			if(alpha[i]==value)
				result=i;
		return result;
	}

	@Override
	public int getValue(int index) {
		return alpha[index];
	}

	@Override
	public int degree(int x1, int x2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
