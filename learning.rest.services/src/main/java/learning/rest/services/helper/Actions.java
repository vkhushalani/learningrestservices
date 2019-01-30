package learning.rest.services.helper;

public class Actions {

	private Boolean approve = false;
	private Boolean reject = false;
	private Boolean cancel = false;
	private Boolean enter = false;
	private Boolean withdraw = false;
	
	public Boolean getApprove() {
		return approve;
	}
	public void setApprove(Boolean approve) {
		this.approve = approve;
	}
	public Boolean getReject() {
		return reject;
	}
	public void setReject(Boolean reject) {
		this.reject = reject;
	}
	public Boolean getCancel() {
		return cancel;
	}
	public void setCancel(Boolean cancel) {
		this.cancel = cancel;
	}
	public Boolean getEnter() {
		return enter;
	}
	public void setEnter(Boolean enter) {
		this.enter = enter;
	}
	public Boolean getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(Boolean withdraw) {
		this.withdraw = withdraw;
	}
}
