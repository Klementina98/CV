// Scroll animations (GSAP + ScrollTrigger). The page is fully readable without this file:
// nothing is hidden by CSS, and every effect is skipped when GSAP is missing or reduced motion is on.
(function () {
  "use strict";
  var reduceMotion = window.matchMedia("(prefers-reduced-motion: reduce)").matches;
  if (reduceMotion || !window.gsap || !window.ScrollTrigger) return;

  var gsap = window.gsap;
  gsap.registerPlugin(window.ScrollTrigger);
  var ease = "power3.out";

  // Hero intro
  gsap.timeline({ defaults: { ease: ease, duration: 1 } })
    .from(".hero-glow", { opacity: 0, scale: 0.8, duration: 1.6 })
    .from(".avatar", { opacity: 0, scale: 0.6, duration: 0.9, ease: "back.out(1.6)" }, 0.1)
    .from(".eyebrow", { opacity: 0, y: 20 }, 0.3)
    .from(".hero h1", { opacity: 0, y: 40, scale: 0.96 }, 0.4)
    .from(".lead", { opacity: 0, y: 30 }, 0.55)
    .from(".cta .btn", { opacity: 0, y: 20, stagger: 0.08, clearProps: "transform" }, 0.7)
    .from(".scroll-hint", { opacity: 0 }, 1.1);

  // Hero recedes as you scroll past it
  gsap.to(".hero-inner", {
    scale: 0.92, opacity: 0, y: -60, ease: "none",
    scrollTrigger: { trigger: ".hero", start: "top top", end: "bottom top", scrub: true },
  });
  gsap.to(".hero-glow", {
    rotate: 25, scale: 1.3, ease: "none",
    scrollTrigger: { trigger: ".hero", start: "top top", end: "bottom top", scrub: true },
  });

  // Generic fade-up reveal
  gsap.utils.toArray(".reveal").forEach(function (el) {
    gsap.from(el, {
      opacity: 0, y: 60, duration: 1, ease: ease, clearProps: "transform",
      scrollTrigger: { trigger: el, start: "top 88%", once: true },
    });
  });

  // App screenshots rise in, then drift at different speeds
  var shots = gsap.utils.toArray(".shot");
  gsap.from(shots, {
    opacity: 0, y: 120, rotateX: 18, transformPerspective: 900, duration: 1.2, ease: ease, stagger: 0.15,
    scrollTrigger: { trigger: ".shots", start: "top 85%", once: true },
  });
  shots.forEach(function (shot, i) {
    gsap.to(shot.querySelector("img"), {
      y: [-30, 30, -15][i] || 0, ease: "none",
      scrollTrigger: { trigger: ".shots", start: "top bottom", end: "bottom top", scrub: true },
    });
  });

  // Talk photo zooms into place
  gsap.fromTo(".talk-photo", { scale: 0.85, opacity: 0.4 }, {
    scale: 1, opacity: 1, ease: "none",
    scrollTrigger: { trigger: ".talk", start: "top 90%", end: "top 40%", scrub: true },
  });

  // Skill tiles pop in
  gsap.from(".tile", {
    opacity: 0, y: 50, scale: 0.94, duration: 0.9, ease: ease, stagger: 0.08, clearProps: "transform",
    scrollTrigger: { trigger: ".bento", start: "top 85%", once: true },
  });

  // About: words light up as you scroll (Apple-style)
  var about = document.querySelector(".about-text");
  if (about) {
    var words = about.textContent.trim().split(/\s+/);
    about.textContent = "";
    words.forEach(function (w, i) {
      var span = document.createElement("span");
      span.className = "word";
      span.textContent = w;
      about.appendChild(span);
      if (i < words.length - 1) about.appendChild(document.createTextNode(" "));
    });
    gsap.to(".about-text .word", {
      opacity: 1, ease: "none", stagger: 0.1,
      scrollTrigger: { trigger: about, start: "top 80%", end: "bottom 45%", scrub: true },
    });
  }
})();
