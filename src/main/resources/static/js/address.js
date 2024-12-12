let provinceData = JSON.parse(localStorage.getItem("provinces"));

function updateAddress() {
   const addressInput = document.getElementById("diachi");
   const province = document.getElementById("provinceSelect");
   const district = document.getElementById("districtSelect");
   const ward = document.getElementById("wardSelect");
   const extra = document.getElementById("extraAddress");

   if (province && district && ward && extra) {
      addressInput.value = province.options[province.selectedIndex].text + ", " + district.options[district.selectedIndex].text + ", " + ward.options[ward.selectedIndex].text + ", " + extra.value;
   } else {
      addressInput.value = "";
   }
}

async function populateDistricts() {
   const provinceSelect = document.getElementById("provinceSelect");
   const districtSelect = document.getElementById("districtSelect");
   const wardSelect = document.getElementById("wardSelect");
   const selectedProvince = provinceSelect.value;

   // Xóa tất cả các tùy chọn của quận/huyện và phường/xã
   districtSelect.innerHTML = "<option value='' selected>Chọn quận/huyện</option>";
   wardSelect.innerHTML = "<option value='' selected>Chọn phường/xã</option>";

   // Tìm tỉnh/thành phố được chọn trong dữ liệu và thêm các quận/huyện tương ứng vào dropdown
   const response = await fetch(`https://esgoo.net/api-tinhthanh/2/${selectedProvince}.htm`);
   const {data} = await response.json();

   if (data) {
      data.forEach(district => {
         let option = document.createElement("option");
         option.text = district.name;
         option.value = district.id;
         districtSelect.add(option);
      });
   } else {
      throw new Error("District data is empty");
   }

   updateAddress();
}

// Hàm này được gọi khi người dùng chọn một quận/huyện
async function populateWards() {
   const districtSelect = document.getElementById("districtSelect");
   const wardSelect = document.getElementById("wardSelect");
   const selectedDistrict = districtSelect.value;

   // Xóa tất cả các tùy chọn của phường/xã
   wardSelect.innerHTML = "<option value='' selected>Chọn phường/xã</option>";

   // Tìm quận/huyện được chọn trong dữ liệu và thêm các phường/xã tương ứng vào dropdown
   const response = await fetch(`https://esgoo.net/api-tinhthanh/3/${selectedDistrict}.htm`);
   const {data} = await response.json();

   if (data) {
      data.forEach(ward => {
         let option = document.createElement("option");
         option.text = ward.name;
         option.value = ward.id;
         wardSelect.add(option);
      });
   }
   updateAddress();
}

// Khởi tạo dropdown tỉnh/thành phố ban đầu
async function fillProvince() {
   const provinceSelect = document.getElementById("provinceSelect");

   if (provinceData == null || provinceData.length === 0) {
      const response = await fetch('https://esgoo.net/api-tinhthanh/1/0.htm');
      const {data} = await response.json();
      if (data) {
         provinceData = data;
         localStorage.setItem("provinces", JSON.stringify(provinceData));
      } else {
         throw new Error("Province data is empty");
      }
   }

   provinceData.forEach(province => {
      let option = document.createElement("option");
      option.text = province.name;
      option.value = province.id;
      provinceSelect.add(option);
   });
}
