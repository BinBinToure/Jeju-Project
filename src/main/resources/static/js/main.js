// 헤더

let header = document.querySelector(".mainList");
let headerHeight = header.offsetHeight;

window.onscroll = function () {
  let windowTop = window.scrollY;
  // 스크롤 세로값이 헤더높이보다 크거나 같으면
  // 헤더에 클래스 'drop'을 추가한다
  if (windowTop >= headerHeight * 6) {
    header.classList.add("drop");
  }
  // 아니면 클래스 'drop'을 제거
  else {
    header.classList.remove("drop");
  }
};

//슬라이드

var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides((slideIndex += n));
}

function currentSlide(n) {
  showSlides((slideIndex = n));
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("demo");
  var captionText = document.getElementById("caption");
  if (n > slides.length) {
    slideIndex = 1;
  }
  if (n < 1) {
    slideIndex = slides.length;
  }
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex - 1].style.display = "block";
  dots[slideIndex - 1].className += " active";
  captionText.innerHTML = dots[slideIndex - 1].alt;
}

//

// Scroll Animation (sa, 스크롤 애니메이션)
const saDefaultMargin = 300;
let saTriggerMargin = 0;
let saTriggerHeight = 0;
const saElementList = document.querySelectorAll(".sa");

const saFunc = function () {
  for (const element of saElementList) {
    if (!element.classList.contains("show")) {
      if (element.dataset.saMargin) {
        saTriggerMargin = parseInt(element.dataset.saMargin);
      } else {
        saTriggerMargin = saDefaultMargin;
      }

      if (element.dataset.saTrigger) {
        saTriggerHeight =
          document
            .querySelector(element.dataset.saTrigger)
            .getBoundingClientRect().top + saTriggerMargin;
      } else {
        saTriggerHeight = element.getBoundingClientRect().top + saTriggerMargin;
      }

      if (window.innerHeight > saTriggerHeight) {
        let delay = element.dataset.saDelay ? element.dataset.saDelay : 0;
        setTimeout(function () {
          element.classList.add("show");
        }, delay);
      }
    }
  }
};

window.addEventListener("load", saFunc);
window.addEventListener("scroll", saFunc);

// background1~4

let mainText = document.querySelector(".background1 h1");
let mainText2 = document.querySelector(".background1 p");
let mainText3 = document.querySelector(".background2 h1");
let mainText4 = document.querySelector(".background2 div:last-child");

window.addEventListener("scroll", function () {
  let value = window.scrollY;


  if (value > 600) {
    mainText.style.animation = "disappear 1s forwards";
  } else {
    mainText.style.animation = `slide_background 1s`;
  }
});

window.addEventListener("scroll", function () {
  let value = window.scrollY;

  if (value > 700) {
    mainText2.style.animation = "disappear2 1s forwards";
  } else {
    mainText2.style.animation = `slide_background2 1s`;
  }
});

window.addEventListener("scroll", function () {
  let value = window.scrollY;

  if (value > 1900) {
    mainText3.style.animation = "slide_background3 1.5s";
  } else {
    mainText3.style.animation = `disappear3 1s forwards`;
  }
});

window.addEventListener("scroll", function () {
  let value = window.scrollY;

  if (value > 2000) {
    mainText4.style.animation = "slide_background4 2s";
  } else {
    mainText4.style.animation = `disappear4 1s forwards`;
  }
});

//로그인

function doDisplay() {
  let con = document.getElementById("myDIV");

  if (con.style.display == "none") {
    con.style.display = "block";
  } else {
    con.style.display = "none";
  }
}

//팝업창


    function pop() {
          window.open("/pop", "pop", "width=496,height=496,history=no,resizable=no,status=no,scrollbars=yes");
        }


// 추천정보

//let Btn1 = document.querySelector('.swiper-slide1');
//let Btn2 = document.querySelector('.swiper-slide2');
//let Btn3 = document.querySelector('.swiper-slide3');
//let menu = document.querySelector('.swiper-container');
//
//
//Btn1.addEventListener('click', ()=> {
//    if(menu.classList.includes("stu_tag_on")){
//    menu.classList.remove('stu_tag_on');
//    }
//    menu.classList.add('stu_tag_on');
//});

function switchDisplay(elem,elem2) {
    let a = document.querySelector('.swiperContainer-a');
    let b = document.querySelector('.swiperContainer-b');
    let c = document.querySelector('.swiperContainer-c');
    let d = document.querySelector('.swiperContainer-d');
    let e = document.querySelector('.swiperContainer-e');
    let f = document.querySelector('.swiperContainer-f');
    let g = document.querySelector('.swiperContainer-g');
    let h = document.querySelector('.swiperContainer-h');
    let i = document.querySelector('.swiperContainer-i');

    a.style.display = "none";
    b.style.display = "none";
    c.style.display = "none";
    d.style.display = "none";
    e.style.display = "none";
    f.style.display = "none";
    g.style.display = "none";
    h.style.display = "none";
    i.style.display = "none";

    elem.style.display = "block";
    let cName = elem2;

//    let textContainer = document.getElementsByClassName("stu_tag ico_theme")
//
//    if(cName.classList == "stu_tag ico_theme stu_tag_on"){
//
//        cName.classList.remove("stu_tag_on");
//        console.log(cName.className);
//
//    }else{
//        for (let i = 0; i < textContainer.length; i++) {
//            textContainer[i].className = textContainer[i].className.add("stu_tag_on");
//          }
//   }
//
}

function initDisplay() {
    document.querySelector('.swiperContainer-b').style.display = "none";
    document.querySelector('.swiperContainer-c').style.display = "none";
    document.querySelector('.swiperContainer-d').style.display = "none";
    document.querySelector('.swiperContainer-e').style.display = "none";
    document.querySelector('.swiperContainer-f').style.display = "none";
    document.querySelector('.swiperContainer-g').style.display = "none";
    document.querySelector('.swiperContainer-h').style.display = "none";
    document.querySelector('.swiperContainer-i').style.display = "none";

}