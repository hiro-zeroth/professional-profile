document.addEventListener("DOMContentLoaded", () => {
  console.log("Experience page loaded successfully.");
  
  // Simple animation for experience cards
  const cards = document.querySelectorAll(".exp-card");
  cards.forEach((card, index) => {
    card.style.opacity = "0";
    card.style.transform = "translateY(20px)";
    setTimeout(() => {
      card.style.transition = "all 0.6s ease";
      card.style.opacity = "1";
      card.style.transform = "translateY(0)";
    }, 200 * index);
  });
});
