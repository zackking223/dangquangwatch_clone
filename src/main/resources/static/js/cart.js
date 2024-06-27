function getCart() {
  let cart = JSON.parse(localStorage.getItem("cart"));

  if (cart == null) {
    cart = {
      tongTong: 0,
      diaChi: "",
      ghiChu: "",
      thanhToan: "Khi nhận hàng",
      NGAYTHEM: "NOW()",
      items: []
    };
  };
  
  saveCart(cart);
  return cart;
}

function saveCart(cart) {
  document.getElementById("cartnum").textContent = cart.items.length

  localStorage.setItem("donhang", JSON.stringify(cart));
}

// Hàm tìm sản phẩm trong giỏ hàng
function findProduct(cart, loaiSanPham, maSanPham) {
  return cart.items.find(item => item.loaiSanPham === loaiSanPham && item.maSanPham === maSanPham);
}

const removeItem = (loaiSanPham, maSanPham) => {
  let cart = getCart();

  cart.items = cart.items.filter(item => item.loaiSanPham !== loaiSanPham && item.maSanPham !== maSanPham);
  cart.tongTien = cart.items.reduce((sum, item) => sum + item.giaTien);

  saveCart(cart);
}

const reduceItem = (tensanpham, maSanPham) => {
  let cart = getCart();
  let product = findProduct(cart, tensanpham, maSanPham);

  if (product) {
    if (product.soLuong > 1) {
      cart.items = cart.items.map(item => {
        if (item.tensanpham === product.tensanpham) {
          item.soLuong -= 1;
          return item;
        } else {
          return item;
        }
      });

      cart.tongTien -= product.giaTien;
    } else {
      removeItem(product.loaiSanPham, product.maSanPham);
    }
  }

  saveCart(cart);
}

const addItem = (tensanpham, maSanPham, loaiSanPham, soLuong, giaTien, anhsanpham) => {
  let cart = getCart();
  let product = findProduct(cart, loaiSanPham, maSanPham);

  if (product) {
    cart.items = cart.items.map(item => {
      if (item.tensanpham === product.tensanpham) {
        item.soLuong += 1;
        return item;
      } else {
        return item;
      }
    })
  } else {
    cart.items.push({
      tensanpham,
      maSanPham,
      loaiSanPham,
      soLuong,
      giaTien,
      anhsanpham,
      NGAYTHEM: "NOW()"
    });
  }

  cart.tongTien += soLuong * giaTien;
  saveCart(cart);
}

