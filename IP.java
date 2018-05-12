
public class IP {
	public String address;
    public int frequency;
    public IP(String newIp){
    	this.address=newIp;
    	this.frequency=1;
    }
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
    
}
