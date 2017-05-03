package com.huyang.dao.po;

import java.util.List;

public class BackMenu {
    private Integer id;

    private String name;

    private Integer parentId;

    private String url;

    private String remark;

    private Byte sort;

    private String icon;

    private Byte accessRoleLevel;
    
    /**
     * 是否被选中
     */
    private Boolean checked;
    
    public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	/**
     * 子菜单列表（查询带有层级关系菜单列表时）
     */
    private List<BackMenu> children;
    
    

    public List<BackMenu> getChildren() {
		return children;
	}

	public void setChildren(List<BackMenu> children) {
		this.children = children;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Byte getAccessRoleLevel() {
        return accessRoleLevel;
    }

    public void setAccessRoleLevel(Byte accessRoleLevel) {
        this.accessRoleLevel = accessRoleLevel;
    }
}