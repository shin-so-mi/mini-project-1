package mini.project.Handler;

import java.util.List;
import mini.project.domain.Member;
import mini.project.domain.Setting;
import mini.project.util.Prompt;

public class MemberHandler {
  List<Member> memberList;

  public MemberHandler(List<Member> list) {
    this.memberList = list;
  }

  public void add() {
    System.out.println("[사용자 등록]");

    int no;

    Member member = new Member();
    no = Prompt.inputInt("번호? ");
    member.setNo(no);
    member.setName(Prompt.inputString("이름? "));

    memberList.add(member);
    Setting.setUserNo(no);
  }

  public void list() {
    System.out.println("[사용자 목록 조회]");
    System.out.println("번호 / 이름 / 정확도");
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      System.out.printf("%d, %s, %f\n",
          member.getNo(),
          member.getName(),
          member.getAccuracy());
    }
  }

  public void update() {
    System.out.println("[사용자 정보 변경]");
    Member member = findByNo(Prompt.inputInt("번호? "));

    if (member == null) {
      System.out.println("해당 번호의 사용자가 없습니다.");
      return;
    }

    String name = Prompt.inputString(
        String.format("이름(%s)? ", member.getName()));

    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("사용자 정보 변경을 취소하였습니다.");
      return;
    }

    member.setName(name);
  }

  public void delete() {
    System.out.println("[사용자 삭제]");
    int no = Prompt.inputInt("번호? ");
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 사용자가 없습니다.");
      return;
    }

    String response = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("사용자 삭제를 취소하였습니다.");
      return;
    }

    memberList.remove(index);
    System.out.println("사용자을 삭제하였습니다.");
  }

  private Member findByNo(int no) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getNo() == no) {
        return member;
      }
    }
    return null;
  }

  private int indexOf(int no) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}