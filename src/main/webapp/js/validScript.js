var allSpacesRe = /\s+/g;
function trim(s) {
    return s.replace(allSpacesRe, "");
}
function validInt(item)
{
    var a1 = document.getElementById(item.id).value;
    var s = a1.toString();
    if (s.length == 0)
        s = "0";
    document.getElementById(item.id).value = s;
}

function startW()
{
    loading.style.visibility = 'visible';
}
function stopW()
{
    loading.style.visibility = 'hidden';
}
function changeColorStatus()
{

    $("#statuslabel").css({'color': 'green'});
}
//j_idt14" class="ui-outputlabel ui-widget
//alert(PF('youtlink').jq.val());
//$("#statuslabel").css({'color': 'red'});
//$("#j_idt15").css({'color': 'red'});
//PF('youtlink').jq.

PrimeFaces.locales ['ru'] = {
    closeText: 'Закрыть',
    prevText: 'Назад',
    nextText: 'Вперёд',
    currentText: 'Home',
    monthNames: ['Январь', 'Февраль' , 'Март' , 'Апрель' , 'Май' , 'Июнь' , 'Июль' , 'Август' , 'Сентябрь','Октябрь','Ноябрь','Декабрь' ],
    monthNamesShort: ['Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июн', 'Июл', 'Авг', 'Сен', 'Окт', 'Ноя', 'Дек' ],
    dayNames: ['Воскресенье', 'Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница', 'Суббота'],
    dayNamesShort: ['Воск','Пон' , 'Вт' , 'Ср' , 'Четв' , 'Пят' , 'Суб'],
    dayNamesMin: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб'],
    weekHeader: 'Неделя',
    FirstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix:'',
    timeOnlyTitle: 'Только время',
    timeText: 'Время',
    hourText: 'Час',
    minuteText: 'Минута',
    secondText: 'Секунда',
    currentText: 'Сегодня',
    ampm: false,
    month: 'Месяц',
    week: 'неделя',
    day: 'День',
    allDayText: 'Весь день'
};