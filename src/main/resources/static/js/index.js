var main = {
   init: function () {
      var _this = this;

      $('#btn-save').on('click', function () {
         _this.save();
      });

      $('#btn-update').on('click', function (){
         _this.update();
      });
      
      $('#btn-delete').on('click', function () {
         _this.delete();
      })

      $('input[type="file"]').on("change", function() {
         var filenames = [];
         var files = this.files;
         if (files.length > 1) {
            filenames.push("파일 (" + files.length + ")");
         } else {
            for (let i in files) {
               if (files.hasOwnProperty(i)) {
                  filenames.push(files[i].name);
               }
            }
         }
         $(this).next(".custom-file-label").html(filenames.join(","));
      });

      if($('#datepicker').find('input').val() === '' || $('#datepicker2').find('input').val() === ''){
         $("#datepicker").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: true,
         }).datepicker('update', new Date());

         $("#datepicker2").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: true,
         }).datepicker('update', new Date());
      } else {
         $("#datepicker").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: true,
         });

         $("#datepicker2").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: true,
         });
      }

      $('#datepicker').on('change', function () {
         var start = new Date($('#datepicker').find('input').val());
         var end = new Date($('#datepicker2').find('input').val());
         if (start > end){
            alert('종료일 보다 늦게 지정할 수 없습니다.');
            $('#datepicker').datepicker('setDate', new Date(end));
         }
      });

      $('#datepicker2').on('change', function () {
         var start = new Date($('#datepicker').find('input').val());
         var end = new Date($('#datepicker2').find('input').val());
         if (start > end){
            alert('시작일 보다 빠르게 지정할 수 없습니다.');
            $('#datepicker2').datepicker('setDate', new Date(start));
         }
      });
   },
   save: function () {
      var data = {
         title: $('#title').val(),
         content: $('#content').val(),
         startDate: new Date($('#datepicker').find('input').val()),
         endDate: new Date($('#datepicker2').find('input').val()),
      }

      var formData = new FormData($('form')[0]);
      formData.append('key', new Blob([JSON.stringify(data)], {type: "application/json"}));

      $.ajax({
         type: 'POST',
         url: '/api/notice/save',
         // dataType: 'json',
         // contentType: 'application/json; charset=utf-8;',
         enctype: 'multipart/form-data',
         //data: JSON.stringify(data)
         data : formData,
         dataType : 'json',
         processData: false,
         contentType: false,
      }).done(function () {
         alert('글이 등록되었습니다.');
         window.location.href = '/';
      }).fail(function (error) {
         alert(JSON.stringify(error));
      });
   },
   update: function () {
      var data = {
         title: $('#title').val(),
         content: $('#content').val(),
         startDate: new Date($('#datepicker').find('input').val()),
         endDate: new Date($('#datepicker2').find('input').val()),
      }

      var id = $('#id').val();

      $.ajax({
         type: 'PUT',
         url: '/api/notice/'+id,
         dataType: 'json',
         contentType: 'application/json; charset=utf-8',
         data: JSON.stringify(data)
      }).done(function () {
         alert('글이 수정되었습니다.');
         window.location.href = '/';
      }).fail(function (error) {
         alert(JSON.stringify(error));
      })
   },
   delete: function () {
      var id = $('#id').val();

      $.ajax({
         type: 'DELETE',
         url: '/api/notice/' + id,
         dataType: 'json',
         contentType: 'application/json; charset=utf-8'
      }).done(function () {
         alert('글이 삭제되었습니다.');
         window.location.href = '/';
      }).fail(function (error) {
         alert(JSON.stringify(error));
      });
   }
};

main.init();

