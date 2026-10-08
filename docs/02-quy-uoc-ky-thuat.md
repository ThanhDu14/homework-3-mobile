# 02 – Quy ước kỹ thuật (hợp đồng giữa các thành viên)

Mục đích: 4 người code **song song** mà khi ghép lại không bị lệch tên lớp, tên ID, tên hàm.
**Không tự ý đổi những tên dưới đây** — nếu cần đổi, báo cả nhóm trước.

- Ngôn ngữ: **Java** (project hiện tại dùng Java, minSdk 29, package `com.example.homework_3`)
- Giao diện: XML Views + `ListView` (không dùng RecyclerView/Compose vì đề yêu cầu tùy biến ListView)

## Cấu trúc file

```
app/src/main/java/com/example/homework_3/
├── MainActivity.java                 (TV1)
├── model/Contact.java                (TV1)
├── data/ContactRepository.java       (TV1)
├── adapter/ContactAdapter.java       (TV3)
└── pagination/Paginator.java         (TV4)

app/src/main/res/
├── layout/activity_main.xml          (TV2)
├── layout/item_contact.xml           (TV2)
├── drawable/avatar_01..15.(png|xml)  (TV1)
├── drawable/bg_item_selected.xml     (TV2)
├── values/colors.xml, strings.xml, dimens.xml  (TV2)

app/src/test/java/com/example/homework_3/
└── PaginatorTest.java                (TV4)
```

## API các lớp Java

### `model/Contact` (TV1)
```java
public class Contact {
    public Contact(String name, String phone, @DrawableRes int avatarResId)
    public String getName()
    public String getPhone()
    public int getAvatarResId()
}
```

### `data/ContactRepository` (TV1)
```java
public final class ContactRepository {
    public static List<Contact> getContacts()   // trả về đúng 15 contact, thứ tự cố định
}
```

### `pagination/Paginator` (TV4) — Java thuần, không phụ thuộc Android để unit test được
```java
public class Paginator<T> {
    public static final int PAGE_SIZE = 5;
    public Paginator(List<T> items)
    public int getPageCount()                  // 15 item → 3
    public List<T> getPage(int pageIndex)       // pageIndex bắt đầu từ 0; trả về tối đa 5 phần tử
    public int getCurrentPage()                 // mặc định 0
    public List<T> goToPage(int pageIndex)      // đặt currentPage và trả về item của trang đó
}
```
Ném `IllegalArgumentException` nếu `pageIndex` ngoài khoảng `[0, getPageCount())`.

### `adapter/ContactAdapter` (TV3)
```java
public class ContactAdapter extends BaseAdapter {
    public ContactAdapter(Context context, List<Contact> items)
    public void setItems(List<Contact> items)          // đổi dữ liệu khi chuyển trang + notifyDataSetChanged()
    public void setSelectedContact(@Nullable Contact c) // item nào == c thì tô nền vàng
    // getCount / getItem / getItemId / getView (dùng ViewHolder, tái sử dụng convertView)
}
```
Lưu ý: lưu **Contact được chọn** (không phải vị trí) để khi chuyển trang rồi quay lại, item vẫn được highlight đúng.

## ID view (TV2 tạo, TV1/TV3 dùng)

### `activity_main.xml`
| ID | Loại | Ghi chú |
|----|------|---------|
| `@+id/main` | root layout | Giữ nguyên (MainActivity dùng cho window insets) |
| `@+id/tvSelected` | TextView | Text mặc định: `@string/you_choose_none` |
| `@+id/lvContacts` | ListView | `android:choiceMode="singleChoice"` |
| `@+id/btnPage1` | Button | text `1` |
| `@+id/btnPage2` | Button | text `2` |
| `@+id/btnPage3` | Button | text `3` |

### `item_contact.xml`
| ID | Loại |
|----|------|
| `@+id/imgAvatar` | ImageView (khoảng 48dp, `scaleType="centerCrop"`) |
| `@+id/tvName` | TextView (họ tên, to hơn) |
| `@+id/tvPhone` | TextView (SĐT) |

### Resource
| Tên | Giá trị gợi ý |
|-----|---------------|
| `@string/you_choose` | `You choose: %1$s` |
| `@string/you_choose_none` | `You choose:` |
| `@color/item_selected` | `#FFE08A` (vàng như hình) |
| `@color/divider` | `#9E9E9E` |
| `@drawable/bg_item_selected` | shape/màu nền dùng `@color/item_selected` |

## Quy ước hành vi

- **Nút "mờ"** = nút của trang hiện tại: `setEnabled(false)` **và** `setAlpha(0.4f)`. Các nút khác: `setEnabled(true)`, `setAlpha(1f)`.
- Khi chuyển trang: **không** xoá lựa chọn ở TextView (TextView vẫn hiện tên người đã chọn trước đó).
- Màn hình xoay ngang: lưu `currentPage` và contact đã chọn vào `onSaveInstanceState` (điểm cộng, không bắt buộc).

## Quy ước Git

- Nhánh: `feature/tv1-data`, `feature/tv2-layout`, `feature/tv3-adapter`, `feature/tv4-pagination`.
- Commit nhỏ, message tiếng Việt hoặc tiếng Anh đều được, ví dụ `Thêm ContactAdapter với ViewHolder`.
- Mỗi người chỉ sửa file thuộc phần mình. File chung (`strings.xml`, `colors.xml`) do **TV2** quản lý — ai cần thêm thì nhắn TV2.
- Tạo Pull Request vào `main`, TV1 review và merge.
