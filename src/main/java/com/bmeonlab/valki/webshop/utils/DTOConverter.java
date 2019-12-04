package com.bmeonlab.valki.webshop.utils;

import com.bmeonlab.valki.webshop.dto.*;
import com.bmeonlab.valki.webshop.model.*;
import com.bmeonlab.valki.webshop.model.enums.RoleType;
import com.bmeonlab.valki.webshop.service.*;
import com.bmeonlab.valki.webshop.utils.exceptions.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductInCartService productInCartService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private CartService cartService;


    // PRODUCT

    public List<ProductDTO> toProductDTOList(List<Product> products) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        if (products == null)
            return productDTOs;

        for (Product product : products) {
            productDTOs.add(toProductDTO(product));
        }
        return productDTOs;
    }

    public ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO =  modelMapper.map(product, ProductDTO.class);
        productDTO.setReviewIds(getReviewIds(product.getReviews()));
        return productDTO;
    }

    public Product toProduct(ProductDTO productDTO) {
        if(manufacturerService.getManufacturerById(productDTO.getManufacturerId()) == null)
            throw new BadRequestException("Invalid Manufacturer ID set for Product.");

        Product product = modelMapper.map(productDTO, Product.class);

        product.setManufacturer(manufacturerService.getManufacturerById(productDTO.getManufacturerId()));
        product.setReviews(getReviewsFromReviewIdList(productDTO.getReviewIds()));

        return product;
    }

    // CUSTOMER

    public List<CustomerDTO> toCustomerDTOList(List<Customer> customers) {
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        if (customers == null)
            return customerDTOs;

        for (Customer customer : customers) {
            customerDTOs.add(toCustomerDTO(customer));
        }
        return customerDTOs;
    }

    public CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        customerDTO.setInvoiceIds(getInvoiceIds(customer.getInvoices()));

        return customerDTO;
    }

    public Customer toCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);

        customer.setInvoices(getInvoicesFromInvoiceIdList(customerDTO.getInvoiceIds()));
        customer.setCart(cartService.getCartById(customerDTO.getCartId()));

        return customer;
    }

    // REVIEWS

    public List<ReviewDTO> toReviewDTOList(List<Review> reviews) {
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        if (reviews == null)
            return reviewDTOs;

        for (Review review : reviews) {
            reviewDTOs.add(toReviewDTO(review));
        }
        return reviewDTOs;
    }

    public ReviewDTO toReviewDTO(Review review) {
        ReviewDTO reviewDTO =  modelMapper.map(review, ReviewDTO.class);
        return reviewDTO;
    }

    public Review toReview(ReviewDTO reviewDTO) {
        if(productService.getProductById(reviewDTO.getProductId()) == null)
            throw new BadRequestException("Invalid Product ID set for Review.");
        if(customerService.getCustomerById(reviewDTO.getCustomerId()) == null)
            throw new BadRequestException("Invalid Customer ID set for Review.");

        Review review = modelMapper.map(reviewDTO, Review.class);

        review.setCustomer(customerService.getCustomerById(reviewDTO.getCustomerId()));
        review.setProduct(productService.getProductById(reviewDTO.getProductId()));

        return review;
    }

    // INVOICES

    public InvoiceDTO toInvoiceDTO(Invoice invoice) {
        InvoiceDTO invoiceDTO = modelMapper.map(invoice, InvoiceDTO.class);

        invoiceDTO.setProductInCartIds(getProductInCartIds(invoice.getProducts()));

        return invoiceDTO;
    }

    public Invoice toInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = modelMapper.map(invoiceDTO, Invoice.class);

        invoice.setCustomer(customerService.getCustomerById(invoiceDTO.getCustomerId()));
        invoice.setProducts(getProductInCartsFromIdList(invoiceDTO.getProductInCartIds()));

        return invoice;
    }

    public List<InvoiceDTO> toInvoiceDTOList(List<Invoice> invoices) {
        List<InvoiceDTO> invoiceDTOs = new ArrayList<>();
        if (invoices == null)
            return invoiceDTOs;

        for (Invoice invoice : invoices) {
            invoiceDTOs.add(toInvoiceDTO(invoice));
        }
        return invoiceDTOs;
    }

    // PRODUCT_IN_CART

    public List<ProductInCartDTO> toProductInCartDTOList(List<ProductInCart> productInCarts) {
        List<ProductInCartDTO> productInCartDTOs = new ArrayList<>();
        if (productInCarts == null)
            return productInCartDTOs;

        for (ProductInCart productInCart : productInCarts) {
            productInCartDTOs.add(toProductInCartDTO(productInCart));
        }
        return productInCartDTOs;
    }

    public ProductInCartDTO toProductInCartDTO(ProductInCart productInCart) {
        ProductInCartDTO productInCartDTO = modelMapper.map(productInCart, ProductInCartDTO.class);

        return productInCartDTO;
    }

    public ProductInCart toProductInCart(ProductInCartDTO productInCartDTO) {
        if(productService.getProductById(productInCartDTO.getProductId()) == null)
            throw new BadRequestException("Invalid Product ID set for ProductInCart.");
        if(cartService.getCartById(productInCartDTO.getCartId()) == null)
            throw new BadRequestException("Invalid Cart ID set for ProductInCart.");

        ProductInCart productInCart = modelMapper.map(productInCartDTO, ProductInCart.class);

        productInCart.setCart(cartService.getCartById(productInCartDTO.getCartId()));
        productInCart.setProduct(productService.getProductById(productInCartDTO.getProductId()));
        productInCart.setInvoice(invoiceService.getInvoiceById(productInCartDTO.getInvoiceId()));

        return productInCart;
    }

    // CART

    public List<CartDTO> toCartDTOList(List<Cart> carts) {
        List<CartDTO> cartDTOs = new ArrayList<>();
        if (carts == null)
            return cartDTOs;

        for (Cart cart : carts) {
            cartDTOs.add(toCartDTO(cart));
        }
        return cartDTOs;
    }

    public CartDTO toCartDTO(Cart cart) {
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        cartDTO.setProductInCartIds(getProductInCartIds(cart.getProducts()));

        return cartDTO;
    }

    public Cart toCart(CartDTO cartDTO) {
        Cart cart = modelMapper.map(cartDTO, Cart.class);

        cart.setCustomer(customerService.getCustomerById(cartDTO.getCustomerId()));
        cart.setProducts(getProductInCartsFromIdList(cartDTO.getProductInCartIds()));

        return cart;
    }

    // ----- HELPERS -----

    private List<Long> getReviewIds(List<Review> reviews) {
        List<Long> ids = new ArrayList<>();
        if (reviews == null)
            return ids;
        for (Review review : reviews) {
            ids.add(review.getId());
        }
        return ids;
    }

    private List<Review> getReviewsFromReviewIdList(List<Long> reviewIds) {

        if(reviewIds == null)
            return null;

        List<Review> reviews = new ArrayList<>();
        for (Long id : reviewIds) {
            reviews.add(reviewService.getReviewById(id));
        }
        return reviews;
    }

    private List<Long> getProductIds(List<Product> products) {
        List<Long> ids = new ArrayList<>();
        if(products == null)
            return ids;

        for (Product product : products) {
            ids.add(product.getId());
        }
        return ids;
    }

    private List<Product> getProductsFromProductIdList(List<Long> productIds) {

        if (productIds == null)
            return null;

        List<Product> products = new ArrayList<>();
        for (Long id: productIds) {
            products.add(productService.getProductById(id));
        }
        return products;
    }

    private List<Long> getProductInCartIds(List<ProductInCart> products) {
        List<Long> ids = new ArrayList<>();
        if(products == null)
            return ids;

        for (ProductInCart product : products) {
            ids.add(product.getId());
        }
        return ids;
    }

    private List<ProductInCart> getProductInCartsFromIdList(List<Long> productIds) {

        if (productIds == null)
            return null;

        List<ProductInCart> products = new ArrayList<>();
        for (Long id: productIds) {
            products.add(productInCartService.getProductInCartById(id));
        }
        return products;
    }

    private List<Long> getInvoiceIds(List<Invoice> invoices) {
        List<Long> ids = new ArrayList<>();
        if(invoices == null)
            return ids;

        for (Invoice invoice : invoices) {
            ids.add(invoice.getId());
        }
        return ids;
    }

    private List<Invoice> getInvoicesFromInvoiceIdList(List<Long> invoiceIds) {

        if (invoiceIds == null)
            return null;

        List<Invoice> invoices = new ArrayList<>();
        for (Long id: invoiceIds) {
            invoices.add(invoiceService.getInvoiceById(id));
        }
        return invoices;
    }

    private List<RoleType> getRoleNames(List<Role> roles) {
        List<RoleType> roleNames = new ArrayList<>();
        if (roles == null)
            return roleNames;

        for (Role role : roles) {
            roleNames.add(role.getName());
        }
        return roleNames;
    }

    private List<Role> getRolesFromRoleNameList(List<RoleType> roleNames) {

        if (roleNames == null)
            return null;

        List<Role> roles = new ArrayList<>();
        for (RoleType roleName : roleNames) {
            roles.add(roleService.findByRoleName(roleName));
        }
        return roles;
    }
}
