package com.capgemini.callforcode.dtos;

public class TutorialVideoDTO {
	private String videoName;
	private String videoLink;
	
	public String getVideoLink() {
		return videoLink;
	}
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	@Override
	public String toString() {
		return "TutorialVideoDTO [videoName=" + videoName + ", videoLink=" + videoLink + "]";
	}
	
	

}
