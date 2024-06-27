function getCart() {
  let cart = JSON.parse(localStorage.getItem("cart"));

  if (cart == null) {
    cart = {
      tongTong: 0,
      diaChi: "",
      ghiChu: "",
      thanhToan: "Khi nhận hàng",
      NGAYTHEM: new Date(),
      items: []
    };
  };

  saveCart(cart);
  return cart;
}

const resetCart = () => {
  let cart = {
    tongTong: 0,
    diaChi: "",
    ghiChu: "",
    thanhToan: "Khi nhận hàng",
    NGAYTHEM: new Date(),
    items: []
  };

  saveCart(cart);
}

function saveCart(cart) {
  let amount = 0;
  if (cart.items.length > 0) {
    amount = cart.items.reduce((sum, item) => sum + item.soLuong, 0);
  }

  document.getElementById("cartnum").textContent = amount;

  localStorage.setItem("cart", JSON.stringify(cart));
}

// Hàm tìm sản phẩm trong giỏ hàng
function findProduct(cart, loaiSanPham, maSanPham) {
  return cart.items.find(item => ((item.loaiSanPham == loaiSanPham) && (item.maSanPham == maSanPham)));
}

const removeItem = (loaiSanPham, maSanPham) => {
  let cart = getCart();

  cart.items = cart.items.filter(item => (item.loaiSanPham != loaiSanPham) || (item.maSanPham != maSanPham));

  let newTongTien = 0;

  if (cart.items.length > 0) {
    newTongTien = cart.items.reduce((sum, item) => sum + (item.giaTien * item.soLuong), 0);
  }

  cart.tongTien = newTongTien;

  saveCart(cart);
}

const increaseItem = (loaiSanPham, maSanPham) => {
  let cart = getCart();
  let product = findProduct(cart, loaiSanPham, maSanPham);
  if (product) {
    cart.items = cart.items.filter(item => {
      if (item.loaiSanPham == product.loaiSanPham && item.maSanPham == product.maSanPham) {
        item.soLuong += 1;
        document.getElementById(`${item.maSanPham}-${item.loaiSanPham}`).textContent = item.soLuong;
        return item;
      } else {
        return item;
      }
    });

    cart.tongTien += product.giaTien;
  }
  saveCart(cart);
}

const reduceItem = (loaiSanPham, maSanPham) => {
  let cart = getCart();
  let product = findProduct(cart, loaiSanPham, maSanPham);

  if (product) {
    if (product.soLuong > 1) {
      cart.items = cart.items.filter(item => {
        if (item.loaiSanPham == product.loaiSanPham && item.maSanPham == product.maSanPham) {
          item.soLuong -= 1;
          document.getElementById(`${item.maSanPham}-${item.loaiSanPham}`).textContent = item.soLuong;
          return item;
        } else {
          return item;
        }
      });

      cart.tongTien -= parseFloat(product.giaTien) * parseFloat(product.soLuong);
    } else {
      removeItem(product.loaiSanPham, product.maSanPham);
    }
  }

  saveCart(cart);
}

const addItem = (tensanpham, maSanPham, loaiSanPham, giaTien, anhsanpham) => {
  let cart = getCart();
  let product = findProduct(cart, loaiSanPham, maSanPham);

  if (product) {
    cart.items = cart.items.filter(item => {
      if (item.tensanpham == product.tensanpham) {
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
      soLuong: 1,
      giaTien,
      anhsanpham,
      NGAYTHEM: new Date()
    });
  }

  cart.tongTien += parseFloat(giaTien);
  saveCart(cart);
}

const dathang = async () => {
  document.getElementById("dathang-btn").disabled = true;

  let cart = getCart();

  await fetch('/profile/dathang', {
    method: "POST",
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(cart)
  });

  resetCart();
  redirectToCart();
}

// Hàm chuyển hướng
const redirectToCart = () => {
  window.location.href = "/profile/giohang";
}

getCart();