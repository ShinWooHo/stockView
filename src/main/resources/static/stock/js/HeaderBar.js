/**
 * 헤더를 그려주는 컴포넌트 이다.
 */
function HeaderBar(parent, style) {
    this.style = style || {};
    this.className = this.style.className || 'HeaderBar';

    this.init(parent);
}
/**
 * 헤더를 레이아웃을 그린다.
 */
HeaderBar.prototype.init = function (parent) {
    this.widget = $('<div>').addClass(this.className).appendTo(parent).get(0);
    this.titleArea = $('<span>').addClass('titleArea').appendTo(this.widget).get(0);
    this.title = $('<span>').addClass('title').appendTo(this.titleArea).get(0);
}
/**
 * 헤더라벨을 설정한다.
 */
HeaderBar.prototype.setLabel = function (html) {
    $(this.title).attr('title', html).text(html);
}