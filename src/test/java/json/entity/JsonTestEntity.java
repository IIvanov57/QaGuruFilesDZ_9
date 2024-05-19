package json.entity;

public class JsonTestEntity {
  private String id;
  private String type;
  private String name;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ImageInner getImage() {
    return image;
  }

  public void setImage(ImageInner image) {
    this.image = image;
  }

  public MemberInner[] getMembers() {
    return members;
  }

  public void setMembers(MemberInner[] members) {
    this.members = members;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  private ImageInner image;
  private MemberInner [] members;

}
