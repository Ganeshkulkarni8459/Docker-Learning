Create the missing directory: mkdir C:\Users\ganuk\AppData\Roaming\npm

Set npm configuration to use the correct path: npm config set prefix C:\Users\ganuk\AppData\Roaming\npm

Run npx command again: npx create-react-app training-app

cd training-app

npm install

npm run build

docker build -t dnyanyog.org/react-app:latest -f dockerfile .

docker run -it --rm -p 8087:80 dnyanyog.org/react-app:latest