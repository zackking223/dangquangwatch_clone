function getCurrentDateFormatted() {
  const today = new Date();

  const year = today.getFullYear();
  const month = String(today.getMonth() + 1).padStart(2, '0'); // Tháng bắt đầu từ 0
  const day = String(today.getDate()).padStart(2, '0');

  return `${year}-${month}-${day}`;
}


function getCardIssuer(cardNumber) {
  cardNumber = cardNumber.split(" ").join("");
  const firstSixDigits = cardNumber.slice(0, 6);

  if (/^4/.test(cardNumber)) {
    return "Visa";
  } else if (/^5[1-5]/.test(cardNumber) || (firstSixDigits >= "222100" && firstSixDigits <= "272099")) {
    return "MasterCard";
  } else if (/^3[47]/.test(cardNumber)) {
    return "American Express";
  } else if (/^6(?:011|5|4[4-9]|22)/.test(cardNumber)) {
    return "Discover";
  } else if (/^35(2[8-9]|[3-8])/.test(cardNumber)) {
    return "JCB";
  } else if (/^3(?:6|8)/.test(cardNumber)) {
    return "Diners Club";
  } else if (/^62/.test(cardNumber)) {
    return "UnionPay";
  } else {
    return "";
  }
}

function getCart() {
  let cart = JSON.parse(localStorage.getItem("cart"));

  if (cart == null) {
    cart = {
      tongTien: 0,
      diaChi: "",
      ghiChu: "Không có",
      thanhToan: "Chưa thanh toán",
      tinhTrang: "Chờ xác nhận",
      NGAYTHEM: getCurrentDateFormatted(),
      items: []
    };
  };

  saveCart(cart);
  return cart;
}

const resetCart = () => {
  let cart = {
    tongTien: 0,
    diaChi: "",
    ghiChu: "Không có",
    thanhToan: "Chưa thanh toán",
    tinhTrang: "Chờ xác nhận",
    NGAYTHEM: getCurrentDateFormatted(),
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

      cart.tongTien -= parseFloat(product.giaTien);
      saveCart(cart);
    } else {
      removeItem(product.loaiSanPham, product.maSanPham);
    }
  }
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
      NGAYTHEM: getCurrentDateFormatted()
    });
  }

  cart.tongTien += parseFloat(giaTien);
  saveCart(cart);
}

const kiemtradonhang = async () => {
  const checkBtn = document.getElementById("dathang-btn");
  checkBtn.style.visibility = "hidden";
  const loadingBtn = document.getElementById("hidden-dathang-btn");
  loadingBtn.classList.remove("hidden");
  loadingBtn.classList.add("flex");

  let cart = getCart();

  cart.diaChi = document.getElementById("diaChi").value;
  cart.ghiChu = document.getElementById("ghiChu").value;

  const response = await fetch('/profile/kiemtradonhang', {
    method: "POST",
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(cart)
  });

  const data = await response.json();

  if (data.status) {
    loadingBtn.classList.remove("flex");
    loadingBtn.classList.add("hidden");
    saveCart(data.data);
    const paymentForm = document.getElementById("paymentForm");
    paymentForm.classList.remove("hidden");
    paymentForm.classList.add("flex");
  } else {
    loadingBtn.classList.add("hidden");
    loadingBtn.classList.remove("flex");
    checkBtn.style.visibility = "visible";
    showNotification({
      title: "Lỗi đặt hàng",
      message: data.message,
      type: "error"
    });
  }
}

// Hàm chuyển hướng
const redirectToOrders = () => {
  window.location.href = "/profile/donhang";
}

// Hàm chuyển hướng
const redirectToCart = () => {
  window.location.href = "/profile/giohang";
}

function formatCurrencyVND(number) {
  if (isNaN(number)) {
    return '';
  }
  number = parseInt(number);
  return number.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
}

getCart();

const globalCardFunc = () => {
  let info = {
    cardNumber: "",
    cardOwner: "",
    verifyCode: "",
    expiryDate: "",
    cardIssuer: "",
    type: "global"
  }

  const setCardNumber = (cardNumber) => {
    info = {
      ...info,
      cardNumber
    }
  }

  const setCardOwner = (cardOwner) => {
    info = {
      ...info,
      cardOwner
    }
  }

  const setVerifyCode = (verifyCode) => {
    info = {
      ...info,
      verifyCode
    }
  }

  const setExpiryDate = (expiryDate) => {
    info = {
      ...info,
      expiryDate
    }
  }

  const setCardIssuer = (cardIssuer) => {
    info = {
      ...info,
      cardIssuer
    }
  }

  const clearInfo = () => {
    info = {
      cardNumber: "",
      cardOwner: "",
      verifyCode: "",
      expiryDate: "",
      cardIssuer: "",
      type: "global"
    }
  }

  const getInfo = () => {
    if (info.cardNumber && info.cardOwner && info.expiryDate && info.cardIssuer && info.verifyCode) {
      return info;
    } else {
      return null;
    }
  }

  return {
    getInfo,
    setCardNumber,
    setCardOwner,
    setVerifyCode,
    setExpiryDate,
    setCardIssuer,
    clearInfo
  }
}

const localCardFunc = () => {
  let info = {
    cardNumber: "",
    cardOwner: "",
    issueDate: "",
    localBank: "",
    type: "local"
  }

  const setCardNumber = (cardNumber) => {
    info = {
      ...info,
      cardNumber
    }
  }

  const setCardOwner = (cardOwner) => {
    info = {
      ...info,
      cardOwner
    }
  }

  const setIssueDate = (issueDate) => {
    info = {
      ...info,
      issueDate
    }
  }

  const setLocalBank = (localBank) => {
    info = {
      ...info,
      localBank
    }
  }

  const clearInfo = () => {
    info = {
      cardNumber: "",
      cardOwner: "",
      expiryDate: "",
      localBank: "",
      type: "local"
    }
  }

  const getInfo = () => {
    if (info.cardNumber && info.cardOwner && info.issueDate && info.localBank) {
      return info;
    } else {
      return null;
    }
  }

  return {
    getInfo,
    setCardNumber,
    setCardOwner,
    setIssueDate,
    setLocalBank,
    clearInfo
  }
}

let CardInfo = globalCardFunc();

function bindGlobalCardFormEvent() {
  document.getElementById('cardNumber').addEventListener('input', function () {
    let cardNumber = this.value.replace(/\D/g, '').substring(0, 16);  // Only allow digits and limit to 16 digits
    let formattedCardNumber = cardNumber.match(/.{1,4}/g)?.join(' ') || cardNumber;
    this.value = formattedCardNumber;  // Add spaces after every 4 digits
  });

  document.getElementById('cardNumber').addEventListener('blur', function () {
    const cardNumber = this.value.replace(/\s+/g, '');  // Remove spaces for validation
    const isValid = /^\d{16}$/.test(cardNumber);

    if (isValid) {
      const virCardNumber = document.getElementById("virCardNumber");
      const cardCompany = document.getElementById("cardCompany");
      const issuer = getCardIssuer(this.value);

      if (issuer) {
        virCardNumber.textContent = this.value;
        cardCompany.textContent = issuer;
        CardInfo.setCardNumber(this.value);
        CardInfo.setCardIssuer(issuer);
      }
      document.getElementById('cardNumberError').style.display = issuer ? 'none' : 'block';
    }
  });

  document.getElementById('expiryDate').addEventListener('blur', function () {
    const expiryDate = this.value;
    const isValid = /^(0[1-9]|1[0-2])\/\d{2}$/.test(expiryDate);
    document.getElementById('expiryDateError').style.display = isValid ? 'none' : 'block';
    if (isValid) {
      const virExpiryDate = document.getElementById("virDate");
      virExpiryDate.textContent = "Ngày hết hạn: " + this.value;
      CardInfo.setExpiryDate(this.value);
    }
  });
  document.getElementById('verifyCode').addEventListener('blur', function () {
    const verifyCode = this.value;
    const isValid = /^\d{3}$/.test(verifyCode);
    document.getElementById('verifyCodeError').style.display = isValid ? 'none' : 'block';
    if (isValid) {
      CardInfo.setVerifyCode(this.value);
    }
  });

  document.getElementById('cardOwner').addEventListener('input', function () {
    const cardOwner = this.value.trim();
    const isValid = /^[A-Za-z\s]+$/.test(cardOwner) && cardOwner.length > 0;
    document.getElementById('cardOwnerError').style.display = isValid ? 'none' : 'block';

    if (isValid) {
      const virCardOwner = document.getElementById("virCardOwner");
      virCardOwner.textContent = this.value;
      CardInfo.setCardOwner(this.value);
    }
  });
}

function bindLocalCardFormEvent() {
  document.getElementById('cardNumber').addEventListener('input', function () {
    let cardNumber = this.value.replace(/\D/g, '').substring(0, 16);  // Only allow digits and limit to 16 digits
    let formattedCardNumber = cardNumber.match(/.{1,4}/g)?.join(' ') || cardNumber;
    this.value = formattedCardNumber;  // Add spaces after every 4 digits
  });

  document.getElementById('cardNumber').addEventListener('blur', function () {
    const cardNumber = this.value.replace(/\s+/g, '');  // Remove spaces for validation
    const isValid = /^\d{16}$/.test(cardNumber);
    document.getElementById('cardNumberError').style.display = isValid ? 'none' : 'block';

    if (isValid) {
      const virCardNumber = document.getElementById("virCardNumber");
      virCardNumber.textContent = this.value;

      CardInfo.setCardNumber(this.value);
    }
  });

  document.getElementById('issueDate').addEventListener('blur', function () {
    const issueDate = this.value;
    const isValid = /^(0[1-9]|1[0-2])\/\d{2}$/.test(issueDate);
    document.getElementById('issueDateError').style.display = isValid ? 'none' : 'block';
    if (isValid) {
      const virIssueDate = document.getElementById("virDate");
      virIssueDate.textContent = "Ngày phát hành: " + this.value;
      CardInfo.setIssueDate(this.value);
    }
  });

  document.getElementById('cardOwner').addEventListener('input', function () {
    const cardOwner = this.value.trim();
    const isValid = /^[A-Za-z\s]+$/.test(cardOwner) && cardOwner.length > 0;
    document.getElementById('cardOwnerError').style.display = isValid ? 'none' : 'block';

    if (isValid) {
      const virCardOwner = document.getElementById("virCardOwner");
      virCardOwner.textContent = this.value;
      CardInfo.setCardOwner(this.value);
    }
  });

  const bankSearchInput = document.getElementById("bankSearch");
  const bankList = document.getElementById("bankList");
  const bankOptions = document.querySelectorAll(".bank-option");

  // Hiển thị danh sách khi focus vào ô tìm kiếm
  bankSearchInput.addEventListener("focus", () => {
    bankList.classList.remove("hidden");
  });

  // Ẩn danh sách khi click ra ngoài
  document.addEventListener("click", (event) => {
    if (!bankList.contains(event.target) && event.target !== bankSearchInput) {
      bankList.classList.add("hidden");
    }
  });

  // Lọc danh sách khi người dùng nhập
  bankSearchInput.addEventListener("input", () => {
    const searchText = bankSearchInput.value.toLowerCase();

    bankOptions.forEach(option => {
      const text = option.textContent.toLowerCase();
      if (text.includes(searchText)) {
        option.classList.remove("hidden");
      } else {
        option.classList.add("hidden");
      }
    });
  });

  // Chọn ngân hàng từ danh sách
  bankOptions.forEach(option => {
    option.addEventListener("click", () => {
      bankSearchInput.value = option.textContent;

      const bankName = option.getAttribute("data-value")

      document.getElementById("cardCompany").textContent = bankName;
      bankSearchInput.setAttribute("data-selected-value", bankName);
      CardInfo.setLocalBank(option.getAttribute("data-value"));
      bankList.classList.add("hidden");
    });
  });
}

function initVirtualCard() {
  document.getElementById("virCard").innerHTML = `
    <p id="cardCompany"></p>
    <h1 id="virCardNumber" class="sm:text-3xl text-2xl"></h1>
    <p id="virDate" class="text-lg"></p>
    <p id="virCardOwner" class="text-xl"></p>
  `;
}

const thanhToanOption = document.getElementById("thanhToanOption");
const paymentContainer = document.getElementById("paymentContainer");

function setCardForm(cardType = "global") {
  const cardForm = document.getElementById("cardForm");

  if (cardType == "global") {
    cardForm.innerHTML = `
      <div class="w-full">
        <label class="block font-medium mb-1 mt-2" for="thanhToan">Số thẻ</label>
        <input class="w-full p-2 rounded border border-black" id="cardNumber" maxlength="19"
          placeholder="XXXX XXXX XXXX XXXX" required />
        <span class="hidden text-sm text-red-500" id="cardNumberError">Số thẻ không hợp lệ</span>
      </div>
      <div class="w-full grid md:grid-cols-2 grid-cols-1 gap-4">
        <div class="w-full">
          <label class="block font-medium mb-1 mt-2" for="expiryDate">Ngày hết hạn</label>
          <input class="w-full p-2 rounded border border-black" id="expiryDate" maxlength="5"
            placeholder="MM/YY" required />
          <span class="hidden text-sm text-red-500" id="expiryDateError">Ngày hết hạn không hợp lệ</span>
        </div>
        <div class="w-full">
          <label class="block font-medium mb-1 mt-2" for="verifyCode">CVV/CVC</label>
          <input class="w-full p-2 rounded border border-black" id="verifyCode" type="password" maxlength="3"
            placeholder="123" required />
          <span class="hidden text-sm text-red-500" id="verifyCodeError">CVV/CVC không hợp lệ</span>
        </div>
      </div>
      <div class="w-full">
        <label class="block font-medium mb-1 mt-2" for="thanhToan">Tên in trên thẻ</label>
        <input class="w-full p-2 rounded border border-black" id="cardOwner" placeholder="NGUYEN VAN A" required />
        <span class="hidden text-sm text-red-500" id="cardOwnerError">Nhập không dấu, có ít nhất 1 dấu cách (Vd:
          LY
          HAI).</span>
      </div>

      <div class="w-full" style="margin-top: 20px;">
        <button type="button" id="checkout-btn" onclick="checkout()"
          class="bg-green-500 hover:bg-yellow-700 text-white font-bold py-2 pl-3 pr-4 rounded flex gap-2 items-center">
          <img src="/svg/von.svg" class="w-5 h-5" alt="pay">
          <p>
            Đặt hàng
          </p>
        </button>
        <button id="hidden-checkout-btn" type="button"
          class="bg-slate-500 text-white font-bold py-2 pl-3 pr-4 rounded gap-2 items-center cursor-wait hidden">
          <img src="/svg/von.svg" class="w-5 h-5" alt="pay">
          <p>
            Đặt hàng
          </p>
        </button>
      </div>
    `;

    CardInfo = globalCardFunc();
    initVirtualCard();
    bindGlobalCardFormEvent();
  } else {
    cardForm.innerHTML = `
      <div class="w-full">
        <label class="block font-medium mb-1 mt-2" for="bankSearch">Ngân hàng</label>
        
        <!-- Ô input để tìm kiếm -->
        <input type="text" id="bankSearch" class="w-full p-2 rounded border border-black" placeholder="Chọn ngân hàng...">
        
        <!-- Danh sách kết quả tìm kiếm -->
        <div id="bankList" class="w-full p-2 rounded border border-black mt-1 max-h-40 overflow-y-auto hidden">
            <div style="padding: 8px; cursor: pointer;" class="hover:bg-yellow-700 bank-option" data-value="Ngân hàng TMCP Quân đội">MB Bank</div>
            <div style="padding: 8px; cursor: pointer;" class="hover:bg-yellow-700 bank-option" data-value="Ngân hàng TMCP Ngoại thương Việt Nam">Vietcombank</div>
            <div style="padding: 8px; cursor: pointer;" class="hover:bg-yellow-700 bank-option" data-value="Ngân hàng TMCP Đầu tư và Phát triển Việt Nam">BIDV</div>
            <div style="padding: 8px; cursor: pointer;" class="hover:bg-yellow-700 bank-option" data-value="Ngân hàng TMCP Công Thương Việt Nam">Vietinbank</div>
            <div style="padding: 8px; cursor: pointer;" class="hover:bg-yellow-700 bank-option" data-value="Ngân hàng Nông nghiệp và Phát triển Nông thôn Việt Nam">Agribank</div>
            <div style="padding: 8px; cursor: pointer;" class="hover:bg-yellow-700 bank-option" data-value="Ngân hàng TMCP Kỹ Thương Việt Nam">Techcombank</div>
            <div style="padding: 8px; cursor: pointer;" class="hover:bg-yellow-700 bank-option" data-value="Ngân hàng TMCP Sài Gòn Thương Tín">Sacombank</div>
            <div style="padding: 8px; cursor: pointer;" class="hover:bg-yellow-700 bank-option" data-value="Ngân hàng TMCP Á Châu">ACB</div>
            <div style="padding: 8px; cursor: pointer;" class="hover:bg-yellow-700 bank-option" data-value="Ngân hàng TMCP Việt Nam Thịnh Vượng">VP Bank</div>
            <div style="padding: 8px; cursor: pointer;" class="hover:bg-yellow-700 bank-option" data-value="Ngân hàng TMCP Phát triển Nhà TP.HCM">HD Bank</div>
            <div style="padding: 8px; cursor: pointer;" class="hover:bg-yellow-700 bank-option" data-value="Ngân hàng TMCP Tiên Phong">TP Bank</div>
            <div style="padding: 8px; cursor: pointer;" class="hover:bg-yellow-700 bank-option" data-value="Ngân hàng TMCP Sài Gòn - Hà Nội">SHB</div>
        </div>
      </div>
      <div class="w-full">
        <label class="block font-medium mb-1 mt-2" for="thanhToan">Số thẻ</label>
        <input class="w-full p-2 rounded border border-black" id="cardNumber" maxlength="19"
          placeholder="XXXX XXXX XXXX XXXX" required />
        <span class="hidden text-sm text-red-500" id="cardNumberError">Số thẻ không hợp lệ</span>
      </div>
      <div class="w-full">
        <label class="block font-medium mb-1 mt-2" for="issueDate">Ngày phát hành</label>
        <input class="w-full p-2 rounded border border-black" id="issueDate" maxlength="5" placeholder="MM/YY"
          required />
        <span class="hidden text-sm text-red-500" id="issueDateError">Ngày phát hành không hợp lệ</span>
      </div>
      <div class="w-full">
        <label class="block font-medium mb-1 mt-2" for="thanhToan">Tên in trên thẻ</label>
        <input class="w-full p-2 rounded border border-black" id="cardOwner" placeholder="NGUYEN VAN A"
          required />
        <span class="hidden text-sm text-red-500" id="cardOwnerError">Nhập không dấu, có ít nhất 1 dấu cách (Vd:
          LY
          HAI).</span>
      </div>

      <div class="w-full" style="margin-top: 20px;">
        <button type="button" id="checkout-btn" onclick="checkout()"
          class="bg-green-500 hover:bg-yellow-700 text-white font-bold py-2 pl-3 pr-4 rounded flex gap-2 items-center">
          <img src="/svg/von.svg" class="w-5 h-5" alt="pay">
          <p>
            Đặt hàng
          </p>
        </button>
        <button id="hidden-checkout-btn" type="button"
          class="bg-slate-500 text-white font-bold py-2 pl-3 pr-4 rounded gap-2 items-center cursor-wait hidden">
          <img src="/svg/von.svg" class="w-5 h-5" alt="pay">
          <p>
            Đặt hàng
          </p>
        </button>
      </div>
    `;

    CardInfo = localCardFunc();
    initVirtualCard();
    bindLocalCardFormEvent();
  }
}

thanhToanOption.addEventListener("change", (ev) => {
  if (ev.target.value === "1") {
    paymentContainer.innerHTML = `
      <div class="w-full">
        <button type="button" id="checkout-btn" onclick="checkout()"
          class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 pl-3 pr-4 rounded flex gap-2 items-center">
          <img src="/svg/danggiao.svg" class="w-5 h-5" alt="order">
          <p>
            Đặt hàng
          </p>
        </button>
        <button type="button" id="hidden-checkout-btn"
          class="bg-slate-500 text-white font-bold py-2 pl-3 pr-4 rounded gap-2 items-center cursor-wait hidden">
          <img src="/svg/danggiao.svg" class="w-5 h-5" alt="order">
          <p>
            Đặt hàng
          </p>
        </button>
      </div>
    `;

    CardInfo.clearInfo();
  } else if (ev.target.value === "card") {
    paymentContainer.innerHTML = `
      <div class="flex items-center gap-4 col-span-2">
        <div>
          <input type="radio" name="cardType" id="globalCardOption" value="global" onchange="setCardForm('global')" checked />
          <label for="globalCardOption">Thẻ quốc tế</label>
        </div>
        <div>
          <input type="radio" name="cardType" id="localCardOption" onchange="setCardForm('local')" value="local" />
          <label for="localCardOption">Thẻ nội địa</label>
        </div>
      </div>
      <section id="cardForm" class="w-full">
        
      </section>

      <section id="virCard"
        class="w-full text-white font-medium flex flex-col"
        style="background-image: url('/svg/cardbg.svg'); background-repeat: no-repeat; background-size: cover; background-position: center; border-radius: 16px; padding: 32px; gap: 32px; aspect-ratio: 16 / 9;">
        
      </section>
    `;
    setCardForm("global");
  } else {
    paymentContainer.innerHTML = `
      <div class="w-full">
        <button type="button" id="checkout-btn" onclick="onlinePay()"
          class="bg-green-500 hover:bg-green-600 text-white font-bold py-2 pl-3 pr-4 rounded flex gap-2 items-center">
          <img src="/svg/von.svg" class="w-5 h-5" alt="pay">
          <p>
            Thanh toán online
          </p>
        </button>
        <button type="button" id="hidden-checkout-btn"
          class="bg-slate-500 text-white font-bold py-2 pl-3 pr-4 rounded gap-2 items-center cursor-wait hidden">
          <img src="/svg/von.svg" class="w-5 h-5" alt="pay">
          <p>
            Thanh toán online
          </p>
        </button>
      </div>
    `;
  }
});


const checkout = () => {
  if (typeof Swal !== "undefined") {
    Swal.fire({
      title: 'Đặt hàng?',
      text: "Hãy chắc chắn bạn đã nhập đúng thông tin.",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Thanh toán',
      cancelButtonText: 'Hủy'
    }).then(async (result) => {
      if (result.isConfirmed) {
        document.getElementById("checkout-btn").style.display = "none";
        document.getElementById("hidden-checkout-btn").style.display = "flex"

        let cart = getCart();

        cart.diaChi = document.getElementById("diaChi").value;
        cart.ghiChu = document.getElementById("ghiChu").value;

        const response = await fetch('/profile/checkout', {
          method: "POST",
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            donHang: cart,
            cardInfo: CardInfo.getInfo()
          })
        });

        const data = await response.json();

        if (data.status) {
          resetCart();
          location.reload();
        } else {
          document.getElementById("checkout-btn").style.display = "flex";
          document.getElementById("hidden-checkout-btn").style.display = "none"
          showNotification({
            title: "Lỗi đặt hàng",
            message: data.message,
            type: "error"
          });
        }
      }
    });
  }
}

const onlinePay = () => {
  if (typeof Swal !== "undefined") {
    Swal.fire({
      title: 'Bạn có chắc chắn muốn thanh toán online?',
      text: "Bạn sẽ không thể quay lại!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Thanh toán',
      cancelButtonText: 'Hủy'
    }).then(async (result) => {
      if (result.isConfirmed) {
        document.getElementById("checkout-btn").style.display = "none";
        document.getElementById("hidden-checkout-btn").style.display = "flex"

        let cart = getCart();

        cart.diaChi = document.getElementById("diaChi").value;
        cart.ghiChu = document.getElementById("ghiChu").value;

        const response = await fetch('/api/vnpay/create-payment', {
          method: "POST",
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            donHang: cart,
            cardInfo: CardInfo.getInfo()
          })
        });

        const data = await response.json();

        if (data.status) {
          location.href = data.message;
        } else {
          document.getElementById("checkout-btn").style.display = "flex";
          document.getElementById("hidden-checkout-btn").style.display = "none"
          showNotification({
            title: "Lỗi đặt hàng",
            message: data.message,
            type: "error"
          });
        }
      }
    });

  } else {
    throw new Error("Swal is not defined");
  }
}
