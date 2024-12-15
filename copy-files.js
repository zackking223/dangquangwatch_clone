var ncp = require('ncp').ncp;
var fs = require('fs');
var path = require('path');

// Đảm bảo thư mục target/classes tồn tại
var targetDir = path.join('target', 'classes');
if (!fs.existsSync(targetDir)) {
    fs.mkdirSync(targetDir, { recursive: true });
}

ncp.limit = 16;

ncp('./src/main/resources', targetDir, {
    filter: (source) => {
        if (fs.lstatSync(source).isDirectory()) {
            return true;
        } else {
            return source.match(process.argv[2]) != null;
        }
    }
}, function (err) {
    if (err) {
        return console.error(err);
    }
    console.log('Copy completed successfully!');
});