package com.example.user.TourTalking.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Agency implements Parcelable{
	private int agency_pk;
	private int company_id;
	private String agency_id;
	private String agency_nickname;
	private String agency_name;
	private String agency_phone;
	private String agency_passwoard;
	
	public int getAgency_pk() {
		return agency_pk;
	}
	public void setAgency_pk(int agency_pk) {
		this.agency_pk = agency_pk;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getAgency_id() {
		return agency_id;
	}
	public void setAgency_id(String agency_id) {
		this.agency_id = agency_id;
	}
	public String getAgency_nickname() {
		return agency_nickname;
	}
	public void setAgency_nickname(String agency_nickname) {
		this.agency_nickname = agency_nickname;
	}
	public String getAgency_name() {
		return agency_name;
	}
	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}
	public String getAgency_phone() {
		return agency_phone;
	}
	public void setAgency_phone(String agency_phone) {
		this.agency_phone = agency_phone;
	}
	public String getAgency_passwoard() {
		return agency_passwoard;
	}
	public void setAgency_passwoard(String agency_passwoard) {
		this.agency_passwoard = agency_passwoard;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.agency_pk);
		dest.writeInt(this.company_id);
		dest.writeString(this.agency_id);
		dest.writeString(this.agency_nickname);
		dest.writeString(this.agency_name);
		dest.writeString(this.agency_phone);
		dest.writeString(this.agency_passwoard);
	}

	public Agency() {
	}

	protected Agency(Parcel in) {
		this.agency_pk = in.readInt();
		this.company_id = in.readInt();
		this.agency_id = in.readString();
		this.agency_nickname = in.readString();
		this.agency_name = in.readString();
		this.agency_phone = in.readString();
		this.agency_passwoard = in.readString();
	}

	public static final Creator<Agency> CREATOR = new Creator<Agency>() {
		@Override
		public Agency createFromParcel(Parcel source) {
			return new Agency(source);
		}

		@Override
		public Agency[] newArray(int size) {
			return new Agency[size];
		}
	};
}
