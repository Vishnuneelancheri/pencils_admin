package com.mainproject.vishnu_neelancheri.pencilsadmin.add_paper;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 3/11/2018
 */

public class AllPaperModel implements Parcelable {
    @SerializedName("id_paper")
    private String idPaper;
    @SerializedName("paper_name")
    private String paperName;
    @SerializedName("id_paper_cartoon_price")
    private String idPaperCartoonPrice;
    @SerializedName("paper_cartoon_price")
    private String paperCartoonPrice;
    @SerializedName("paper_id")
    private String paperId;
    @SerializedName("id_paper_portrait_price")
    private String idPaperPortraitPrice;
    @SerializedName("paper_portrait_price")
    private String paperPortraitPrice;

    public String getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(String idPaper) {
        this.idPaper = idPaper;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getIdPaperCartoonPrice() {
        return idPaperCartoonPrice;
    }

    public void setIdPaperCartoonPrice(String idPaperCartoonPrice) {
        this.idPaperCartoonPrice = idPaperCartoonPrice;
    }

    public String getPaperCartoonPrice() {
        return paperCartoonPrice;
    }

    public void setPaperCartoonPrice(String paperCartoonPrice) {
        this.paperCartoonPrice = paperCartoonPrice;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getIdPaperPortraitPrice() {
        return idPaperPortraitPrice;
    }

    public void setIdPaperPortraitPrice(String idPaperPortraitPrice) {
        this.idPaperPortraitPrice = idPaperPortraitPrice;
    }

    public String getPaperPortraitPrice() {
        return paperPortraitPrice;
    }

    public void setPaperPortraitPrice(String paperPortraitPrice) {
        this.paperPortraitPrice = paperPortraitPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idPaper);
        dest.writeString(this.paperName);
        dest.writeString(this.idPaperCartoonPrice);
        dest.writeString(this.paperCartoonPrice);
        dest.writeString(this.paperId);
        dest.writeString(this.idPaperPortraitPrice);
        dest.writeString(this.paperPortraitPrice);
    }

    public AllPaperModel() {
    }

    protected AllPaperModel(Parcel in) {
        this.idPaper = in.readString();
        this.paperName = in.readString();
        this.idPaperCartoonPrice = in.readString();
        this.paperCartoonPrice = in.readString();
        this.paperId = in.readString();
        this.idPaperPortraitPrice = in.readString();
        this.paperPortraitPrice = in.readString();
    }

    public static final Parcelable.Creator<AllPaperModel> CREATOR = new Parcelable.Creator<AllPaperModel>() {
        @Override
        public AllPaperModel createFromParcel(Parcel source) {
            return new AllPaperModel(source);
        }

        @Override
        public AllPaperModel[] newArray(int size) {
            return new AllPaperModel[size];
        }
    };
}
