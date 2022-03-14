package ordilov.lissn.member.adapter.in.web;

import lombok.Value;

public class MemberDto {

  @Value
  public static class UpdateRequest{
    String name;
    String picture;
  }
}
