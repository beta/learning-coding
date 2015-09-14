var courseCount = 1;

$('.wam-add-course').click(function() {
  if (courseCount < 5) {
    courseCount++;
    $('.course' + courseCount).fadeIn();
  }
});
