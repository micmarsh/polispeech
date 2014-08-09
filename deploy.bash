git checkout gh-pages
git add main.css speeches.js index.html
DATE=$(date)
git commit -m "Deploy $DATE"
git push origin gh-pages
git checkout master