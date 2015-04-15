import java.io.Serializable;


public class Message implements Serializable{
	private  int [] sourceMessage;
	private  int [] messageWithError;
	public int[] getSourceMessage() {
		return sourceMessage;
	}
	public void setSourceMessage(int[] sourceMessage) {
		this.sourceMessage = sourceMessage;
	}
	public int[] getMessageWithError() {
		return messageWithError;
	}
	public void setMessageWithError(int[] messageWithError) {
		this.messageWithError = messageWithError;
	}
	public Message(int [] sourceMessage,int [] error){
		setSourceMessage(sourceMessage);
		messageWithError=new int[sourceMessage.length];
		for(int i=0;i<sourceMessage.length;i++)
			messageWithError[i]=sourceMessage[i]^error[i];
	}
}
