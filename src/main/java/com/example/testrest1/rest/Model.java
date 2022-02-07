package com.example.testrest1.rest;

import java.util.List;

@lombok.Data
public class Model {

    private List<SnapshotVos> snapshotVos;
    private int code;
    private String msg;

    public List<SnapshotVos> getSnapshotVos() {
        return snapshotVos;
    }

    public void setSnapshotVos(List<SnapshotVos> snapshotVos) {
        this.snapshotVos = snapshotVos;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

class SnapshotVos {
    private String type;
    private Long updateTime;
    private List<String> data;

    public SnapshotVos() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}

class Data {
    private String totalAssetOfBtc;
    private List<Balance> balances;

    public Data() {
    }

    public String getTotalAssetOfBtc() {
        return totalAssetOfBtc;
    }

    public void setTotalAssetOfBtc(String totalAssetOfBtc) {
        this.totalAssetOfBtc = totalAssetOfBtc;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }
}

class Balance {
    private String asset;
    private String free;
    private String locked;

    public Balance() {
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }
}
