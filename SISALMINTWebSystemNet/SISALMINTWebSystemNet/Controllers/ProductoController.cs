﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using SISALMINTWebSystemNet.Models;
using SISALMINTWebSystemNet.ViewModel.ProductoViewModel;

namespace SISALMINTWebSystemNet.Controllers
{
    public class ProductoController : Controller
    {
        public ActionResult LstProducto()
        {
            LstProductoViewModel objViewModel = new LstProductoViewModel();
            objViewModel.Fill();
            return View(objViewModel);
        }

        [HttpPost]
        public ActionResult LstProducto(LstProductoViewModel objViewModel)
        {
            objViewModel.Fill();
            return View("LstProducto", "_Layout", objViewModel);
        }

        public ActionResult DetalleProducto(string codigoProducto)
        {
            DetalleProductoViewModel objViewModel = new DetalleProductoViewModel();
            objViewModel.Fill(codigoProducto);
            return View("DetalleProducto", "_Layout", objViewModel);
        }

        public ActionResult AddEditProducto(string codigoProducto)
        {
            AddEditProductoViewModel objViewModel = new AddEditProductoViewModel();
            objViewModel.Fill(codigoProducto);
            objViewModel.objProducto.FechaIngreso = Convert.ToDateTime("1999-01-01");
            objViewModel.codigoProducto = objViewModel.objProducto.Codigo;
            return View("AddEditProducto", "_Layout", objViewModel);
        }

        [HttpPost]
        public ActionResult AddEditProducto(AddEditProductoViewModel objViewModel)
        {
            try
            {
                objViewModel.objProducto.Codigo = objViewModel.codigoProducto;
                if (objViewModel.tieneValor)
                {

                    objViewModel.ModificarProducto(objViewModel.objProducto);
                }
                else
                    objViewModel.RegistrarProducto(objViewModel.objProducto);

                String MensajeRespuesta = objViewModel.tieneValor ? "El Producto se actualizó correctamente." : "El Producto se registró correctamente.";
                TempData["objMensaje"] = new KeyValuePair<String, String>("SUC", MensajeRespuesta);

                objViewModel.Fill("");
                objViewModel.objProducto.FechaIngreso = Convert.ToDateTime("1999-01-01");
                return View("AddEditProducto", "_Layout", objViewModel);
            }
            catch (Exception ex)
            {
                string a = ex.Message;
                TempData["objMensaje"] = new KeyValuePair<String, String>("ERR", "Por favor intente más tarde.");
                return View(objViewModel);
            }

        }

        public ActionResult EliminarProducto(string codigoProducto)
        {
            try
            {
                AddEditProductoViewModel objViewModel = new AddEditProductoViewModel();
                objViewModel.EliminarProducto(codigoProducto);

                TempData["objMensaje"] = new KeyValuePair<String, String>("SUC", "El Producto ha sido eliminado.");
                return RedirectToAction("LstProducto");
            }
            catch (Exception)
            {

                TempData["objMensaje"] = new KeyValuePair<String, String>("ERR", "No se ha podido eliminar al Cliente.");
                return RedirectToAction("LstProducto");
            }

        }
    }
}