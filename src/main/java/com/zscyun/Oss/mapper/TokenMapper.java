package com.zscyun.Oss.mapper;

import com.zscyun.Oss.entity.Token;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 蛋炒饭不加蛋
 * @date 2023/3/20
 */
@Mapper
public interface TokenMapper {
  /**
   * 创建token
   *
   * @param token token
   * @return 结果
   */
  public int createToken(Token token);

  /**
   * 查询所有token
   *
   * @param userId 用户id
   * @return 结果
   */
  public List<Token> selectTokens(Long userId);

  /**
   * 删除token
   *
   * @param tokenId tokenId
   * @return 结果
   */
  public int deleteToken(Integer tokenId);
}
