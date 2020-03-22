package com.lessly.center.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 用户菜单资源及操作菜单权限
 */
@Data
public class UserPermsDto implements Serializable {

    List<MenuDto> userMenus;

    Set<String> perms;



}
