package com.example.user.TourTalking.domain.mian;

import android.os.Parcel;
import android.os.Parcelable;

public class TrvImageUrl implements Parcelable {
	private  int trv_image_id;
	private String image_url;
	
	public int getTrv_image_id() {
		return trv_image_id;
	}
	public void setTrv_image_id(int trv_image_id) {
		this.trv_image_id = trv_image_id;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.trv_image_id);
		dest.writeString(this.image_url);
	}

	public TrvImageUrl() {
	}

	protected TrvImageUrl(Parcel in) {
		this.trv_image_id = in.readInt();
		this.image_url = in.readString();
	}

	public static final Creator<TrvImageUrl> CREATOR = new Creator<TrvImageUrl>() {
		@Override
		public TrvImageUrl createFromParcel(Parcel source) {
			return new TrvImageUrl(source);
		}

		@Override
		public TrvImageUrl[] newArray(int size) {
			return new TrvImageUrl[size];
		}
	};
}
